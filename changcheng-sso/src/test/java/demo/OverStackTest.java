package demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OverStackTest {

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


  public static void main(String[] args) throws Exception {
    System.out.println("--方法开始--");
    new OverStackTest().foo3();
  }
}
