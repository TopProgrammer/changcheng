package site.changcheng.admin.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.changcheng.admin.Service.SSOService;
import site.changcheng.admin.web.request.LoginForm;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/user")
public class UserController {

  @Resource
  private SSOService ssoService;
  @RequestMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody LoginForm form){
    ssoService.checkLoginState(form);

    return ResponseEntity.ok("SUCCESS");
  }
}
