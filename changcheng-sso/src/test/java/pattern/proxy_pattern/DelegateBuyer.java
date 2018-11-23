package pattern.proxy_pattern;

public class DelegateBuyer implements BuyHouse {

  @Override public boolean buyHouse() {
    System.out.println("张三买房子了================================");
    return false;
  }
}
