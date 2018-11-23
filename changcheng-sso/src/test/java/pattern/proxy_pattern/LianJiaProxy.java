package pattern.proxy_pattern;

public class LianJiaProxy implements BuyHouse{

  private BuyHouse buyHouse;
  public LianJiaProxy(BuyHouse delegateBuyer) {
    buyHouse = delegateBuyer;
  }

  @Override public boolean buyHouse() {

    System.out.println("选择合适的房源");
    buyHouse.buyHouse();
    System.out.println("办理房产证");
    return false;
  }
}
