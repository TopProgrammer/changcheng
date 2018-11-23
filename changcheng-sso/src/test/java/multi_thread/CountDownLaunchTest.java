package multi_thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLaunchTest {
  private CountDownLatch latch = new CountDownLatch(1);

  @Test
  public void name() throws Exception {
    for(int i=0;i<10;i++){
      new ThreadCount().start();
    }
  }

  class ThreadCount extends Thread{
    public void run(){
      latch.countDown();
    }
  }
}
