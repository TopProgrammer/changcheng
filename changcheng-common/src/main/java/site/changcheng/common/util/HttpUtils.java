package site.changcheng.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class HttpUtils {

  private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final String DEFAULT_POST_FORM_HEAD="application/x-www-form-urlencoded";
  /**
   * <pre>
   * post发送FORM表单数据请求
   * 设置了Content-Type=application/x-www-form-urlencoded
   * </pre>
   *
   * @param url：请求路径
   * @param
   * @return
   * @throws Exception
   */
  public static JSONObject doPostForm(String url, String formString,String head) throws Exception {
    LOG.info("doPost请求内容：{}", formString);

    HttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.addHeader("text/plain", CharEncoding.UTF_8);
    post.addHeader("Content-Type", StringUtils.isBlank(head)?DEFAULT_POST_FORM_HEAD:head);

    StringEntity s = new StringEntity(formString, DEFAULT_CHARSET);
    post.setEntity(s);

    HttpResponse httpResponse = client.execute(post);

    JSONObject response = null;
    String result = EntityUtils.toString(httpResponse.getEntity());
    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
      LOG.info("doPostJson响应结果：{}", result);
      response = JSONObject.parseObject(result);
    } else {
      throw new RuntimeException(result);
    }
    return response;
  }


}
