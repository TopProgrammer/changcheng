package pattern.proxy_pattern;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {

  public static void main(String args[]) {
    BuyHouse buyHouse = new DelegateBuyer();
    BuyHouse proxyBuyHouse = (BuyHouse) Proxy
        .newProxyInstance(BuyHouse.class.getClassLoader(), new Class[] { BuyHouse.class },
            new DynamicProxyHandler(buyHouse));
    proxyBuyHouse.buyHouse();
  }
}
