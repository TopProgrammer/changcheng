package site.changcheng.sso.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/demo")
public class DemoController {

  @RequestMapping("/demo")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("SUCCESS");
  }
}
