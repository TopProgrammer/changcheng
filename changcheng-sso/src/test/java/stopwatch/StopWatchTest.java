package stopwatch;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class StopWatchTest {

  private Logger LOG = LoggerFactory.getLogger(getClass());

  @Test
  public void testStopWatch(){
    StopWatch watch = new StopWatch();

    try {
      watch.start("watch1");
      Thread.sleep(2000L);
      watch.stop();

      watch.start("watch2");
      Thread.sleep(1030L);
      watch.stop();

      LOG.info(watch.prettyPrint());

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
