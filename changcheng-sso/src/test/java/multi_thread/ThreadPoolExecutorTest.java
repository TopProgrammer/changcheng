package multi_thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
  public static void main(String args[]) {
    int corePoolSize = 4;//核心线程
    int maximumPoolSize = 40;//最大线程数量
    int keepAliveTime = 100;//空余保留时间

    /**
     * 创建一个具有缓冲功能的线程池
     * return ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
     */
    ExecutorService cachedPpool = Executors.newCachedThreadPool();
    /**
     * 创建一个可重用的，具有固定线程数的线程池
     * new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>());
     */
    ExecutorService fixedPool = Executors.newFixedThreadPool(5);
    /**
     * 创建一个只有一个单线程的线程池
     * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>());
     */
    ExecutorService singlePool = Executors.newSingleThreadExecutor();

    /**
     * 创建一个具有指定线程数的线程池，它可以在指定的延迟后执行线程任务
     * new ScheduledThreadPoolExecutor(corePoolSize)
     */
    ExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
    /**
     * 创建一个只有一个单线程的线程池,它可以在指定的延迟后执行线程任务
     *  return new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1)
     */
    ExecutorService singleSchedulePool = Executors.newSingleThreadScheduledExecutor();

    /**
     * 创建持有足够线程的线程池来支持给定的并行级别，该方法会使用多个队列来减少竞争。
     * return new ForkJoinPool(parallelism, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
     */
    ExecutorService worksStealingPool = Executors.newWorkStealingPool(10);
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
        TimeUnit.MILLISECONDS, // 时间单位
        new LinkedBlockingQueue<Runnable>(3));//默认构造的队列大小为Integer.Max, 可指定大小new LinkedBlockingQueue<Runnable>(3)，队列容量为3
    for (int i = 0; i < 10; i++) {
      System.out.println(" i的值为" + i);
      Runnable thread = () -> {
        System.out.println("=====");
      };

      threadPool.submit(thread);
      threadPool.execute(new SimpleThread(String.valueOf(i)));
    }
  }
}
