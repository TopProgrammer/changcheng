package site.changcheng.admin.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter
@Component
public class LoginFilter implements Filter {

  private Logger LOG = LoggerFactory.getLogger(getClass());
  @Override public void init(FilterConfig filterConfig) throws ServletException {
    LOG.info("======登录过滤器初始化=======");
  }

  @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {

  }

  @Override public void destroy() {
    LOG.info("======登陆过滤器销毁=========");
  }
}
