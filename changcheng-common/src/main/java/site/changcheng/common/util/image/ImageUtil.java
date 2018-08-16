package site.changcheng.common.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片工具类
 *
 */
public class ImageUtil {

  /**
   * 获取登录图片验证码
   * 
   * @return
   */
  public static ImageCaptcha getLoginImageCaptcha() {
    // 生成随机类
    Random random = new Random();
    int width = 104, height = 34;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // 获取图形上下文
    Graphics g = image.getGraphics();
    // 设定背景色
    g.setColor(Color.WHITE);
    g.drawRect(0, 0, width, height);
    g.setColor(new Color(170, 232, 212));
    g.fillRect(0, 0, width , height );
    // 设定字体
    g.setFont(new Font("微软雅黑", Font.BOLD, 25));
    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
    g.setColor(new Color(255, 255, 240,190));
    for (int i = 0; i < 50; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(i % 20 == 0 ? 0 : x, (i % 2 - 1) == 0 ? 0 : y, x + xl + 20, y + yl + 20);
    }
    // 取随机产生的认证码(4位数字)
    String codeList = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
    String sRand = "";
    for (int i = 0; i < 4; i++) {
      int a = random.nextInt(codeList.length() - 1);
      String rand = codeList.substring(a, a + 1);
      sRand += rand;
      // 将认证码显示到图象中
      g.setColor(new Color(255, 255, 255));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
      g.translate(random.nextInt(3), random.nextInt(3));
      g.drawString(rand, 23 * i + 6, 26);
    }
    // 图象生效
    g.dispose();
    ImageCaptcha captcha = new ImageCaptcha();
    captcha.captcha = sRand;
    captcha.image = image;
    return captcha;
  }

}
