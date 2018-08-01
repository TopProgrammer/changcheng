package site.changcheng.admin.web.request;

import io.swagger.annotations.ApiModel;

@ApiModel("登陆实体bean")
public class LoginForm {
  private String username;
  private String password;
  private String picCaptcha;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPicCaptcha() {
    return picCaptcha;
  }

  public void setPicCaptcha(String picCaptcha) {
    this.picCaptcha = picCaptcha;
  }
}
