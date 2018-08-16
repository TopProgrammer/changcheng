package site.changcheng.common.http.session;

/**
 * session事件类型
 *
 * @author caoxudong
 * @since 0.1.0
 */
public enum SessionEventType {
  /**
   * 创建session
   */
  CREATE,
  /**
   * 销毁session
   */
  DESCTROY,
  /**
   * session属性变动
   */
  ATTRIBUTE_CHANGE,
  /**
   * 激活session
   */
  ACTIVATE, 
  /**
   * 换出，即将session暂时从内存中排出，进行持久化
   */
  PASSIVATE, 
  /**
   * session失效
   */
  INVALIDATE;
}
