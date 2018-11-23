
import org.junit.Test;

public class SwtichTest {

  public void SwitchTest() throws Exception {
  }

  @Test
  public void getValue() {
    int i=2;
    int result = 0;
    switch (i) {
    case 1:
      result = result + i;
    case 2:
      result = result + 2 * i;
    case 3:
      result = result + 3 * i;
    case 4:
      result = result + 4 * i;
    }
    System.out.println(result);
  }
}
