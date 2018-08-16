package site.changcheng.common.exception;

/**
 * 错误异常信息类
 */
public enum ChangchengException {

  CHANGHCNEG_USER_PWD_ERROR(5001, "用户名密码错误"),
  CHANGHCNEG_USER_NOT_EXISTS(5002, "用户不存在错误"),
  CHANGHCNEG_PIC_CAPTCHA_ERROR(5003, "图形验证码错误"),

  CHANGHCNEG_SYSTEM_ERROR(500, "服务器错误"),
  ;

  public String errDesc;
  public Integer errCode;

  ChangchengException(Integer errCode, String errDesc) {
    this.errDesc = errDesc;
    this.errCode = errCode;
  }
}
