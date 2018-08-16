package site.changcheng.common.http.session.sharedsession;

import cc.uworks.common.http.session.SessionEvent;
import cc.uworks.common.http.session.SessionEventType;
import cc.uworks.common.http.session.SessionListener;
import com.zbj.cache.spring.support.redis.cachecloud.exi.cluster.ClusterRedisCacheEntry;

/**
 * 根据不同的session事件，对redis中缓存的session对象做处理
 *
 * @author caoxudong
 * @since 0.1.0
 */
public class RedisSessionListener implements SessionListener {

  /**
   * 该字段的值由{@link RedisSessionFilter}在初始化时设置
   * 
   * FIXME: 这个设置太别扭
   */
  public static ClusterRedisCacheEntry clusterRedisCacheEntry;
  public static String sharedSessionCacheKeyPrefix;

  @Override
  public void fire(SessionEvent event) {
    SessionEventType eventType = event.getType();
    switch (eventType) {
      case INVALIDATE: {
        /**
         * 需要将redis中的session置为失效
         */
        SessionWrapper session = (SessionWrapper) event.getData();
        String sid = session.getId();
        clusterRedisCacheEntry.getRedisCluster().del(sharedSessionCacheKeyPrefix + sid);
        break;
      }

      case CREATE:
        break;

      default:
        break;
    }
  }

}
