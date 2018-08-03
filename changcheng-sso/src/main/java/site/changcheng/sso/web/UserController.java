package site.changcheng.sso.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.changcheng.sso.constants.Constants;
import site.changcheng.sso.web.request.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

  @RequestMapping(value = "/login",method = RequestMethod.POST)
  public ResponseEntity<String> login(@Valid @RequestBody LoginForm form,HttpServletRequest request) {

    String pwd = form.getPassword();
    String picCode = form.getPicCaptcha();
    String userName = form.getUsername();

    // 校验验证码
    HttpSession session = request.getSession(false);
    if (session == null) {
      // 抛异常
    }
    String captchaCache = (String) session
        .getAttribute(Constants.WEB_LOGIN_IMAGE_CODE);
    if (captchaCache == null) {
      // 抛异常

    }

    if (!(picCode.trim()).equalsIgnoreCase(captchaCache)) {
      // 抛异常
    }

    LOG.info("=========图形验证码校验成功==开始登陆校验登陆=============");
//    request.getSession().setAttribute("user", user);
    Map<String, Object> returnData = new HashMap<String, Object>();
    //session.removeAttribute(Constants.WEB_LOGIN_IMAGE_CODE);
    return ResponseEntity.ok().body("Success");
  }

}
