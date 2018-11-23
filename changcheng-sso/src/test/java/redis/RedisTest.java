package redis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.changcheng.common.redis.RedisUtil;
import site.changcheng.sso.App;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
  @Autowired
  private RedisUtil redisUtil;

  @Test
  public void findRedis() {
    redisUtil.set("userName", "hello wenqy");
    Object obj = redisUtil.get("userName");
    HashMap<String,Object> map = new HashMap<>();
    redisUtil.hmset("baidu",map);
    System.out.println(JSONObject.toJSONString(obj));
  }
}
