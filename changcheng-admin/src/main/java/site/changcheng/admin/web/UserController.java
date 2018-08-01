package site.changcheng.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api
public class UserController extends BaseController {

  @Resource
  private SSOService ssoService;
  @RequestMapping("/login")
  @ApiOperation("登陆")
  public ResponseEntity<String> login(@Valid @RequestBody LoginForm form){
    ssoService.checkLoginState(form);

    LOG.info("===============================");

    return ResponseEntity.ok("SUCCESS");
  }
}
