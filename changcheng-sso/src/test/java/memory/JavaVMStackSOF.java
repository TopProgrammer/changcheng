package memory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaVMStackSOF {
  private int stackLength = 1;
  public void statckLeak(){
    stackLength++;
    statckLeak();
  }

  public static void main(String args[]){
    JavaVMStackSOF oom = new JavaVMStackSOF();
    try {
      oom.statckLeak();
    }catch (Throwable e){
      System.out.println("stack length:"+oom.stackLength);
      throw e;
    }
  }

  @Test
  public void foo1() throws Exception {
    for(int i=0;i<200000000;i++){
      i-=1;
    }
  }

  @Test
  public void foo2() throws Exception {
    List<Integer> list = new ArrayList<>();
    for(int i=0;i<200000000;i++){
      list.add(i);
    }
  }


  @Test
  public void foo3() throws Exception {
    System.out.println("--foo3方法开始--");
    bar();
    System.out.println("--foo3方法结束--");
  }

  public void bar() throws Exception {
    foo3();
    System.out.println("--bar running--");
  }

  @Test
  public void foo4() throws Exception {
    foo4();
  }

}
