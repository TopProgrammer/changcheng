package site.changcheng.admin.Service.impl;

import cc.changcheng.common.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import site.changcheng.admin.web.request.LoginForm;

public class SSOClient {
  private String httpUrl = "http://127.0.0.1:7000/user/login";

  public void connectedSSO(LoginForm form) throws Exception {
    String str = JSONObject.toJSONString(form);
    HttpUtils.doPostForm(httpUrl,str,"");
  }
}
