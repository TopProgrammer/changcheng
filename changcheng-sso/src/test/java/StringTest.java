
import org.junit.Test;

public class StringTest {

  @Test
  public void equal() throws Exception {
    String str1 = "hello";
    String str2= "he";
    String str3 = "llo";


    System.out.print(str1 == (str2 + str3));
    System.out.print(str1.equals (str2 + str3));
  }
}
