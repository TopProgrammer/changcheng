package site.changcheng.common.http.session.sharedsession;

import cc.uworks.common.utils.SerializeUtils;
import com.zbj.cache.spring.support.redis.cachecloud.exi.cluster.ClusterRedisCacheEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 共享session存储在redis中。获取共享session的过滤器。 应该在使用session对象之前，先经过该过滤器。
 * 
 * <p>
 * 该过滤器会使用的应用初始化参数参见{@link SharedSessionFilter}类中的定义：
 * 
 * <p>
 * 针对该过滤器，可以设置以下初始化参数：
 * <ul>
 * <li>sharedSessionRedisClientBeanId:
 * 配置于spring中的MemCachedClient对象的bean的id，默认为"clusterRedisCacheEntry"</li>
 * </ul>
 *
 * @author caoxudong
 * @since 0.1.0
 */
public class RedisSessionFilter implements SharedSessionFilter {

  private static final Logger LOG = LoggerFactory.getLogger(RedisSessionFilter.class);

  public static final String CONFIG_PROP_NAME_REDIS_CLIENT_BEAN_ID = "sharedSessionRedisClientBeanId";

  // 共享session相关的属性
  private String sharedRedisClientBeanId = "clusterRedisCacheEntry";
  private int sharedSessionTimeout = CONFIG_PROP_VALUE_SESSION_TIMEOUT;
  private String sharedSessionCacheKeyPrefix = CONFIG_PROP_VALUE_SHARED_SESSION_KEY_PREFIX;
  private String sharedSessionIdCookieName = CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_NAME;
  private boolean sharedSessionIdCookieSecure = CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_SECURE;
  private boolean sharedSessionIdCookieHttpOnly = CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_HTTPONLY;
  private String sharedSessionIdCookieDomain = CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_DOMAIN;
  private String sharedSessionIdCookiePath = CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_PATH;
  private String deleteSessionCookieRequestAttribute = CONFIG_REQUEST_ATTRIBUTE_NAME_DELETE_SHARED_SESSION_ID_COOKIE;

  // 通过redis存储共享session
  private ClusterRedisCacheEntry clusterRedisCacheEntry;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    ServletContext servletContext = filterConfig.getServletContext();

    // 设置clusterRedisCacheEntry -----------------------------------------------
    String initRedisClientBeanId = filterConfig.getInitParameter(CONFIG_PROP_NAME_REDIS_CLIENT_BEAN_ID);
    if ((initRedisClientBeanId != null) && (!"".equals(initRedisClientBeanId))) {
      this.sharedRedisClientBeanId = initRedisClientBeanId;
    }
    WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    // this.clusterRedisCacheEntry =
    // (ClusterRedisCacheEntry)wac.getBean(this.sharedRedisClientBeanId);
    this.clusterRedisCacheEntry = wac.getBean(ClusterRedisCacheEntry.class);
    RedisSessionListener.clusterRedisCacheEntry = this.clusterRedisCacheEntry;

    // 设置session cookie ------------------------------------------------
    String interval = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_TIMEOUT);
    if ((interval != null) && (!"".equals(interval))) {
      this.sharedSessionTimeout = Integer.parseInt(interval);
    }

    String sessionKeyPrefix = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_KEY_PREFIX);
    if ((null != sessionKeyPrefix) && (!"".equals(sessionKeyPrefix))) {
      this.sharedSessionCacheKeyPrefix = sessionKeyPrefix;
    }
    RedisSessionListener.sharedSessionCacheKeyPrefix = this.sharedSessionCacheKeyPrefix;

    String sessionIdCookieName = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_NAME);
    if ((sessionIdCookieName != null) && (!"".equals(sessionIdCookieName))) {
      this.sharedSessionIdCookieName = sessionIdCookieName;
    }

    String sessionIdCookieHttpOnly =
        servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_HTTPONLY);
    if ((null != sessionIdCookieHttpOnly) && (!"".equals(sessionIdCookieHttpOnly))) {
      this.sharedSessionIdCookieHttpOnly = Boolean.getBoolean(sessionIdCookieHttpOnly);
    }

    String sessionIdCookieSecure = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_SECURE);
    if ((null != sessionIdCookieSecure) && (!"".equals(sessionIdCookieSecure))) {
      this.sharedSessionIdCookieSecure = Boolean.getBoolean(sessionIdCookieSecure);
    }

    String sessionIdCookieDomain = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_DOMAIN);
    if ((null != sessionIdCookieDomain) && (!"".equals(sessionIdCookieDomain))) {
      this.sharedSessionIdCookieDomain = sessionIdCookieDomain;
    }

    String sessionIdCookiePath = servletContext.getInitParameter(SharedSessionFilter.CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_PATH);
    if ((null != sessionIdCookiePath) && (!"".equals(sessionIdCookiePath))) {
      this.sharedSessionIdCookiePath = sessionIdCookiePath;
    }

    // session监听器
    SessionWrapper.addSessionLister(new RedisSessionListener());
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    RequestWrapper requestWrapper = null;

    // 查找是否已经设置过sessionID
    Cookie[] cookies = request.getCookies();
    Cookie sessionIdCookie = null;
    String sessionId = null;
    if ((cookies != null) && (cookies.length > 0)) {
      for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        if (this.sharedSessionIdCookieName.equals(name)) {
          sessionIdCookie = cookie;
          sessionId = value;
          break;
        }
      }
    }

    SessionWrapper session = null;
    if (sessionIdCookie == null) {
      // 之前没有访问过
      requestWrapper = new RequestWrapper(request);
      session = (SessionWrapper) requestWrapper.getSession();
    } else {
      // 之前已经访问过的用户
      // 先从redis中获取session对象
      session = getSessionFormRedis(this.sharedSessionCacheKeyPrefix + sessionId);
      if (session == null) {
        // 没有或者已经过期
        requestWrapper = new RequestWrapper(request);
        session = (SessionWrapper) requestWrapper.getSession();
        session.setId(sessionId);
      } else {
        session.setNew(false);
        session.setLastAccessedTime(System.currentTimeMillis());
        requestWrapper = new RequestWrapper(request, session);
      }
    }

    // 设置cookie
    sessionId = session.getId();
    Cookie sessionIdCookieAfterService = new Cookie(this.sharedSessionIdCookieName, sessionId);
    sessionIdCookieAfterService.setMaxAge(this.sharedSessionTimeout);
    if (null != this.sharedSessionIdCookieDomain) {
      sessionIdCookieAfterService.setDomain(this.sharedSessionIdCookieDomain);
    }
    sessionIdCookieAfterService.setPath(this.sharedSessionIdCookiePath);
    if (this.sharedSessionIdCookieHttpOnly) {
      sessionIdCookieAfterService.setHttpOnly(true);
    }
    if (this.sharedSessionIdCookieSecure) {
      sessionIdCookieAfterService.setSecure(this.sharedSessionIdCookieSecure);
    }
    response.addCookie(sessionIdCookieAfterService);

    // 执行其他业务
    chain.doFilter(requestWrapper, response);

    // 当具有特殊属性时，要删除session cookie
    Object deleteCookieRequestAttributeObj = request.getAttribute(deleteSessionCookieRequestAttribute);
    if (null != deleteCookieRequestAttributeObj) {
      Cookie deleteSessionIdCookie = new Cookie(this.sharedSessionIdCookieName, sessionId);
      deleteSessionIdCookie.setMaxAge(0);
      if (null != this.sharedSessionIdCookieDomain) {
        deleteSessionIdCookie.setDomain(this.sharedSessionIdCookieDomain);
      }
      deleteSessionIdCookie.setPath(this.sharedSessionIdCookiePath);
      if (this.sharedSessionIdCookieHttpOnly) {
        deleteSessionIdCookie.setHttpOnly(true);
      }
      if (this.sharedSessionIdCookieSecure) {
        deleteSessionIdCookie.setSecure(this.sharedSessionIdCookieSecure);
      }
      response.addCookie(sessionIdCookieAfterService);
      clusterRedisCacheEntry.getRedisCluster().del(this.sharedSessionCacheKeyPrefix + sessionId);
    } else {
      // 写回redis
      if (session.getAttributesCount() > 0) {
        setSessionToRedis(this.sharedSessionCacheKeyPrefix + sessionId, session);
      }
    }

  }

  @Override
  public void destroy() {

  }

  @Override
  public int getSharedSessionTimeout() {
    return this.sharedSessionTimeout;
  }

  @Override
  public String getSharedSessionIdCookieName() {
    return this.sharedSessionIdCookieName;
  }

  @Override
  public String getSharedSessionCacheKeyPrefix() {
    return this.sharedSessionCacheKeyPrefix;
  }

  private void setSessionToRedis(String key, SessionWrapper session) {
    clusterRedisCacheEntry.getRedisCluster().setex(key.getBytes(), this.sharedSessionTimeout, SerializeUtils.serizlize(session));
  }

  private SessionWrapper getSessionFormRedis(String key) {
    byte[] bytes = clusterRedisCacheEntry.getRedisCluster().get(key.getBytes());
    if (bytes != null) {
      return (SessionWrapper) SerializeUtils.deserialize(bytes);
    }
    return null;
  }
}
