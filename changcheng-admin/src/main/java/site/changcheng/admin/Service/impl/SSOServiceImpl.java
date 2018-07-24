package site.changcheng.admin.Service.impl;

import cc.changcheng.common.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import site.changcheng.admin.Service.SSOService;
import site.changcheng.admin.web.request.LoginForm;

import javax.validation.Valid;

/**
 * 拦截子系统未登录用户请求，跳转至sso认证中心
 * 接收并存储sso认证中心发送的令牌
 * 与sso-server通信，校验令牌的有效性
 * 建立局部会话
 * 拦截用户注销请求，向sso认证中心发送注销请求
 * 接收sso认证中心发出的注销请求，销毁局部会话
 */
@Service
public class SSOServiceImpl implements SSOService {

  @Override public Boolean checkLoginState(@Valid LoginForm form) {
    return null;
  }


  public void connectedSSO(LoginForm form) throws Exception {
    String url = "http://127.0.0.1:7000/user/login";
    String str = JSONObject.toJSONString(form);
    HttpUtils.doPostForm(url,str,"");
  }
}
