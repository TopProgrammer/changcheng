package site.changcheng.admin.Service;

import site.changcheng.admin.web.request.LoginForm;

import javax.validation.Valid;

public interface SSOService {

  Boolean checkLoginState(@Valid LoginForm form);
}
