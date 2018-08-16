package site.changcheng.common.http.session.sharedsession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class RequestWrapper extends HttpServletRequestWrapper {

  private SessionWrapper internalSession;

  /**
   * 创建包装request，同时指定包装session
   * @param request
   * @param session
   */
  public RequestWrapper(HttpServletRequest request, SessionWrapper session) {
    super(request);
    if ((request == null) || (session == null)) {
      throw new IllegalArgumentException(
          "Arguments request or session cannot be null.");
    }
    this.internalSession = session;
  }
  
  /**
   * 创建包装request，同时在内部创建包装session对象
   * @param request
   */
  public RequestWrapper(HttpServletRequest request) {
    super(request);
    if (request == null) {
      throw new IllegalArgumentException("Arguments request cannot be null.");
    }
    getSession(true);
  }

  @Override
  public HttpSession getSession(boolean create) {
    SessionWrapper session = null;
    if (create) {
      session =  new SessionWrapper();
      this.internalSession = session;
    } else {
      if (internalSession != null) {
        session = internalSession;
      }
    }
    return session;
  }

  @Override
  public HttpSession getSession() {
    if (this.internalSession == null) {
      return this.getSession(true);
    } else {
      return this.internalSession;
    }
  }

  @Override
  public String changeSessionId() {
    String newSessionId = SessionWrapper.generateSessionId();
    this.internalSession.id = newSessionId;
    return newSessionId;
  }

}
