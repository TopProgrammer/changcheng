package site.changcheng.common.http.session.sharedsession;

import cc.uworks.common.http.session.SessionEvent;
import cc.uworks.common.http.session.SessionEventType;
import cc.uworks.common.http.session.SessionListener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 对{@link HttpSession}默认实现的包装， 将数据存储在内部的{@link SessionWrapper#data}中。
 * 
 * <p>
 * 目前，有一些session相关的特性还没有实现，包括:
 * <ul>
 * <li>不支持SessionContext</li>
 * <li>监听session属性的变动，不支持{@link HttpSessionAttributeListener}</li>
 * <li>监听session属性的变动，不支持{@link HttpSessionAttributeListener}</li>
 * </ul>
 * 
 * TODO: 1.实现对session属性变动的监听; 2. 多线程安全性还未考虑太多
 *
 * @author caoxudong
 * @since 0.1.0
 * @see SessionEventType
 */
public class SessionWrapper implements HttpSession, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 默认情况下，session有效期为30分钟。可以在{@link MemcachedSessionFilter}中设置。
   */
  public static int defaultSessionTimeout = 30 * 60;

  /**
   * 共享session缓存的key的前缀
   */
  public static String defaultSessionCacheKeyPrefix = "session_";

  /**
   * session id属性的名字，使用该名字在cookie中查找对应的属性值
   */
  public static String defaultSessionIdCookieName = "sessId";
  
  /**
   * 默认不设置session cookie的Secure属性
   */
  public static boolean defaultSessionIdCookieSecure = true;

  /**
   * 默认设置session cookie的HttpOnly属性
   */
  public static boolean defaultSessionIdCookieHttpOnly = true;

  /**
   * 默认设置cookie的域名为网站自己的域名
   */
  public static String defaultSessionIdCookieDomain = null;

  /**
   * 默认设置cookie的路径为根路径
   */
  public static String defaultSessionIdCookiePath = "/";

  protected static List<SessionListener> listeners = new LinkedList<>();
  
  protected Map<String, Object> data = new ConcurrentHashMap<>();
  protected boolean isNew = true;
  protected boolean isValid = true;
  protected String id;
  protected long creationTime = System.currentTimeMillis();
  protected long lastAccessedTime = this.creationTime;
  protected int maxInactiveInterval = SharedSessionFilter.CONFIG_PROP_VALUE_SESSION_TIMEOUT;

  public SessionWrapper() {
    super();
    this.id = generateSessionId();
  }

  @Override
  public long getCreationTime() {
    checkValidity();
    return this.creationTime;
  }

  @Override
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public long getLastAccessedTime() {
    checkValidity();
    return this.lastAccessedTime;
  }

  public void setLastAccessedTime(long lastAccessedTime) {
    this.lastAccessedTime = lastAccessedTime;
  }

  @Override
  public ServletContext getServletContext() {
    return null;
  }

  @Override
  public void setMaxInactiveInterval(int interval) {
    this.maxInactiveInterval = interval;
  }

  @Override
  public int getMaxInactiveInterval() {
    return this.maxInactiveInterval;
  }

  @Override
  public HttpSessionContext getSessionContext() {
    return null;
  }

  @Override
  public Object getAttribute(String name) {
    checkValidity();
    if (null == name) {
      return null;
    } else {
      return this.data.get(name);
    }
  }

  @Override
  public Object getValue(String name) {
    checkValidity();
    if (null == name) {
      return null;
    } else {
      return this.data.get(name);
    }
  }

  @Override
  public Enumeration<String> getAttributeNames() {
    checkValidity();
    Set<String> names = new HashSet<>();
    names.addAll(this.data.keySet());
    return Collections.enumeration(names);
  }

  @Override
  public String[] getValueNames() {
    checkValidity();
    Set<String> keyNamesSet = this.data.keySet();
    String[] keyNames = new String[keyNamesSet.size()];
    keyNames = keyNamesSet.toArray(keyNames);
    return keyNames;
  }

  @Override
  public void setAttribute(String name, Object value) {
    checkAttributeName(name);
    checkValidity();
    if (null == value) {
      this.removeAttribute(name);
    } else {
      this.data.put(name, value);
    }
  }

  @Override
  public void putValue(String name, Object value) {
    checkAttributeName(name);
    checkValidity();
    if (null == value) {
      this.removeAttribute(name);
    } else {
      this.data.put(name, value);
    }
  }

  @Override
  public void removeAttribute(String name) {
    checkValidity();
    this.data.remove(name);
  }

  @Override
  public void removeValue(String name) {
    checkValidity();
    this.data.remove(name);
  }

  @Override
  public void invalidate() {
    checkValidity();
    this.data.clear();
    this.isValid = true;
    for (SessionListener listener : listeners) {
      listener.fire(new SessionEvent(this, SessionEventType.INVALIDATE, this));
    }
  }

  @Override
  public boolean isNew() {
    checkValidity();
    return this.isNew;
  }

  public void setNew(boolean isNew) {
    this.isNew = isNew;
  }

  /**
   * 添加session事件监听器
   * 
   * @param sessionListener 事件监听器
   * @see SessionListener
   * @see SessionEventType
   */
  public static void addSessionLister(SessionListener sessionListener) {
    listeners.add(sessionListener);
  }

  /**
   * 检查session是否已经失效了
   */
  private void checkValidity() {
    if (!isValid) {
      throw new IllegalStateException("Session is invalid.");
    }
  }

  /**
   * 检查session属性的名字
   * 
   * @param name session属性的名字
   */
  private void checkAttributeName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Attribute name cannot be null.");
    }
  }

  public static String generateSessionId() {
    return Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
  }
  
  /**
   * 获取写入的属性的个数
   * @return
   */
  public int getAttributesCount() {
    return this.data.size();
  }
}
