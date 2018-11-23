package demo;

public class DataTypeTest implements Runnable{
  private static int count=0;


  public static void main(String args[]) throws Exception {

    for(int i=0;i<10;i++){
      Thread test = new Thread(new DataTypeTest());
      test.start();
    }

    System.out.println(count);

  }


  public void run() {
    for (int i = 0; i < 10; i++){
      count++;
      System.out.println("=="+count);
    }

  }
}

class ThreadTest extends Thread {


}

class Count{
  public static volatile int num=0;

}