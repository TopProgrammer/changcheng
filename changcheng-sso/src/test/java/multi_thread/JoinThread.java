package multi_thread;

public class JoinThread extends Thread {
  public JoinThread(String name) {
    super(name);
  }

  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(getName() + "    " + i);
    }
  }

  public static void main(String args[]) throws InterruptedException {
    new JoinThread("新线程").start();
    for(int i=0;i<100;i++){
      if(i==20){
        JoinThread jt = new JoinThread("被JOIN的线程");
        jt.start();
        jt.join();
      }

      if(i==50){
        JoinThread jt2 = new JoinThread("被JOIN的线程2");
        jt2.start();
        jt2.join();
      }
      System.out.println(Thread.currentThread().getName()+"    "+i);
    }
  }

}
