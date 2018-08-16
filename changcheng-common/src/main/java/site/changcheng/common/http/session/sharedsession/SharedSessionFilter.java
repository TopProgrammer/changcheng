package site.changcheng.common.http.session.sharedsession;


import javax.servlet.Filter;

/**
 * 共享session过滤器。
 *
 * @author caoxudong
 * @since 0.1.0
 */
public interface SharedSessionFilter extends Filter {

  /**
   * session有效期，单位是秒
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_TIMEOUT = "sharedSessionTimeout";
  /**
   * 默认情况下，session有效期为30分钟。可以在{@link MemcachedSessionFilter}中设置。
   */
  int CONFIG_PROP_VALUE_SESSION_TIMEOUT = 30 * 60;

  /**
   * 共享session中存储session时使用的key的前缀
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_KEY_PREFIX = "sharedSessionCacheKeyPrefix";
  /**
   * 共享session缓存的key的前缀
   */
  String CONFIG_PROP_VALUE_SHARED_SESSION_KEY_PREFIX = "session_";


  /**
   * session id属性的名字，使用该名字在cookie中查找对应的属性值
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_NAME = "sharedSessionIdCookieName";
  /**
   * session id属性的名字，使用该名字在cookie中查找对应的属性值
   */
  String CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_NAME = "sessId";
  
  /**
   * 是否设置session cookie的httponly属性
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_HTTPONLY = "sharedSessionIdCookieHttpOnly";
  /**
   * 默认不设置session cookie的Secure属性
   */
  boolean CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_HTTPONLY = true;

  /**
   * 是否设置session cookie的secure属性
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_SECURE = "sharedSessionIdCookieSecure";
  /**
   * 默认设置session cookie的HttpOnly属性
   */
  boolean CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_SECURE = true;
  
  /**
   * 设置session cookie的domain属性
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_DOMAIN = "sharedSessionIdCookieDomain";
  /**
   * 默认设置cookie的域名为网站自己的域名
   */
  String CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_DOMAIN = null;
  
  /**
   * 设置session cookie的path属性
   */
  String CONFIG_PROP_NAME_SHARED_SESSION_ID_COOKIE_PATH = "sharedSessionIdCookiePath";
  /**
   * 默认设置cookie的路径为根路径
   */
  String CONFIG_PROP_VALUE_SHARED_SESSION_ID_COOKIE_PATH = "/";
  
  /**
   * 当请求中，带有该变量值指定的属性时，过滤器会在请求结束后，删除session cookie
   */
  String CONFIG_REQUEST_ATTRIBUTE_NAME_DELETE_SHARED_SESSION_ID_COOKIE = "requestAttributeNameDeleteSharedSessionIdCookie";
  
  /**
   * 获取session的超时时间，单位为秒
   */
  int getSharedSessionTimeout(); 
  
  /**
   * 获取sessionID的名字
   */
  String getSharedSessionIdCookieName();
  
  /**
   * 获取存储共享session时使用的key的前缀
   */
  String getSharedSessionCacheKeyPrefix();
}
