package site.changcheng.common.http.session;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpSession;
import java.util.EventObject;

/**
 * session中的事件对象
 *
 * @author caoxudong
 * @since 0.1.0
 */
public final class SessionEvent extends EventObject {

  private static final long serialVersionUID = 1L;

  /**
   * 事件数据
   */
  private final Object data;

  /**
   * 发生事件的session对象
   */
  private final HttpSession session;

  /**
   * 事件类型
   */
  private final SessionEventType type;

  public SessionEvent(HttpSession session, SessionEventType type, Object data) {
    super(session);
    this.session = session;
    this.type = type;
    this.data = data;
  }

  /**
   * 获取事件数据
   */
  public Object getData() {
    return this.data;
  }

  /**
   * 获取发生了事件的session对象
   */
  public HttpSession getSession() {
    return this.session;
  }

  /**
   * 获取事件类型
   */
  public SessionEventType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
