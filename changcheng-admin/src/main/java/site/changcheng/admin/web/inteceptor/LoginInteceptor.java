package site.changcheng.admin.web.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author changcheng
 */
public class LoginInteceptor implements HandlerInterceptor{

  private Logger LOG = LoggerFactory.getLogger(getClass());
  @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    LOG.info("====登录过滤器拦截处理中======");
    Boolean isLogin = (Boolean) session.getAttribute("isLogin");
    if (isLogin!=null&&isLogin) {
//      chain.doFilter(request, response);
      return true;
    }
    // 跳转至sso认证中心
    res.sendRedirect("http://local.obaymax.com:7000/html/index.html?fromurl=https://ureactor.zbj.com");
    return false;
  }

  @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {

  }

  @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable Exception ex) throws Exception {

  }
}
