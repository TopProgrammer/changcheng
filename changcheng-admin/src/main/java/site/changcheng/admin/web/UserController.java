package site.changcheng.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.changcheng.admin.Service.SSOService;
import site.changcheng.admin.web.request.LoginForm;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户中心接口")
@CrossOrigin
public class UserController extends BaseController {





}
