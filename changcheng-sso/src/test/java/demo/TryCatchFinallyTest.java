package demo;

public class TryCatchFinallyTest {

  @SuppressWarnings("finally")
  public static final String test() {
    String t = "";

    try {
      t = "try";
//      throw new RuntimeException();
      return t;
    } catch (Exception e) {
      t = "catch";
      return t;
    } finally {
      t = "finally";
//      return t;
    }
  }

  public static void main(String[] args) {
    System.out.print(TryCatchFinallyTest.test());
  }

}