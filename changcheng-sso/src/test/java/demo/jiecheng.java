package demo;

import org.junit.Test;

public class jiecheng {

  @Test
  public void jiechenghe() throws Exception {
    System.out.println(jiecheng(6));
  }

  public int jiecheng(int i) {
    int result = 1;
    int sum = 0;
    for (int j=1;j<=i;j++){
      result = result * j;
      sum = sum+ result;
    }
    return sum;
  }
}
