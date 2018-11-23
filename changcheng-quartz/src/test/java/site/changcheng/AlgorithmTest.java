package site.changcheng;

import org.junit.Test;

public class AlgorithmTest {


  @Test
  public void test(){
    count(1237);
  }

  public void count(int n){
    if((n)<=5000){
      n = n*2;
      System.out.print(n+",");
      count(n);
    }

  }
}
