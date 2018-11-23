package multi_thread;

public class SimpleThread extends Thread {
  public void run(){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(" 这是一个普通的线程类  ");
  }

  public SimpleThread(String name) {
    super(name);
  }
}
