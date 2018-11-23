package pattern.proxy_pattern;

public class CglibProxyMain {
  public static void main(String args[]) {
    BuyHouse buyHouse = new DelegateBuyer();
    CglibProxy cglibProxy = new CglibProxy();
    BuyHouse proxyBuyHouse = (BuyHouse) cglibProxy.getInstance(buyHouse);
    proxyBuyHouse.buyHouse();
  }
}
