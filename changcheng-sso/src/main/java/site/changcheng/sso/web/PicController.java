package site.changcheng.sso.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.changcheng.common.util.image.ImageCaptcha;
import site.changcheng.common.util.image.ImageUtil;
import site.changcheng.sso.constants.Constants;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api
@RestController
@RequestMapping(value = "/valiedate")
public class PicController extends BaseController{


  /**
   * <pre>
   * 登陆-请求验证码
   * 请求方式：GET
   * 请求路径：/user/loginCode
   * 请求参数：
   * 返回数据：
   *                  图片
   * </pre>
   *
   * @param request
   * @param response
   * @return String
   */
  @RequestMapping(value = "/loginCode", method = { RequestMethod.GET })
  public void loginCode(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    LOG.info("开始发送验证码!");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");
    // 获得图片以及验证码,
    ImageCaptcha imageCaptcha = ImageUtil.getLoginImageCaptcha();
    // 将其保存到缓存中去
    request.getSession().removeAttribute(Constants.WEB_LOGIN_IMAGE_CODE);
    request.getSession().setAttribute(Constants.WEB_LOGIN_IMAGE_CODE,
        imageCaptcha.captcha);
    LOG.info(request.getSession().getAttribute(Constants.WEB_LOGIN_IMAGE_CODE).toString());
    // 输出图象到页面
    ImageIO.write(imageCaptcha.image, "JPEG", response.getOutputStream());
    response.flushBuffer();
  }

}


