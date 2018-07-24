package site.changcheng.sso.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.changcheng.sso.web.request.LoginForm;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/user")
public class UserController {

  @RequestMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody LoginForm form){
    return ResponseEntity.ok("SUCCESS");
  }
}
