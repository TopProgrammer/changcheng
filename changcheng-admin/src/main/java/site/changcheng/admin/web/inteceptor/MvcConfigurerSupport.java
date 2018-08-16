package site.changcheng.admin.web.inteceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Springboot2.0踩坑之WebMvcConfigurerAdapter过时
 *
 * @author changcheng
 */
@Configuration
@EnableWebMvc
public class MvcConfigurerSupport extends WebMvcConfigurationSupport {


  /*
  * 何为跨域
  *mozilla : CORS
  *浏览器同源政策及其规避方法
  *跨域资源共享 CORS 详解
  *
  *4.1 预检请求
  *非简单请求是那种对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json。
  *非简单请求的CORS请求，会在正式通信之前，增加一次HTTP查询请求，称为”预检”请求（preflight）。
  *浏览器先询问服务器，当前网页所在的域名是否在服务器的许可名单之中，以及可以使用哪些HTTP动词和头信息字段。只有得到肯定答复，浏览器才会发出正式的XMLHttpRequest请求，否则就报错。
 */
  @Override
  protected void addCorsMappings(CorsRegistry registry) {
    super.addCorsMappings(registry);
    registry.addMapping("/**")
        .allowedMethods("*")
        .allowedOrigins("*")
        .allowedHeaders("*");
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInteceptor());
    super.addInterceptors(registry);
  }
}
