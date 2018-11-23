package site.changcheng.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class RedisJava {
  public static void main(String[] args) {
    //连接本地的 Redis 服务
    Jedis jedis = new Jedis("47.95.236.75",7000);
    System.out.println("连接成功");
    //查看服务是否运行
    System.out.println("服务正在运行: "+jedis.ping());
  }

  @Test
  public void testString() throws Exception {
    //连接本地的 Redis 服务
    Jedis jedis = new Jedis("47.95.236.75",7000);
    System.out.println("连接成功");
    //设置 redis 字符串数据
    jedis.set("runoobkey", "www.runoob.com");
    // 获取存储的数据并输出
    System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
  }

  @Test
  public void testClustor() throws Exception {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    // 最大连接数
    poolConfig.setMaxTotal(5);
    // 最大空闲数
    poolConfig.setMaxIdle(5);
    // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
    // Could not get a resource from the pool
    poolConfig.setMaxWaitMillis(10000);
    Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
    nodes.add(new HostAndPort("47.95.236.75", 7000));
    nodes.add(new HostAndPort("47.95.236.75", 7001));
    nodes.add(new HostAndPort("47.95.236.75", 7002));
    nodes.add(new HostAndPort("47.95.236.75", 7003));
    nodes.add(new HostAndPort("47.95.236.75", 7004));
    nodes.add(new HostAndPort("47.95.236.75", 7005));
    JedisCluster cluster = new JedisCluster(nodes, poolConfig);

    cluster.set("age", "18");
    System.out.println(cluster.get("age"));
    try {
      cluster.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}