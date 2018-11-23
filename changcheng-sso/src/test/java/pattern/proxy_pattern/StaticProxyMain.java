package pattern.proxy_pattern;

public class StaticProxyMain {

  /**
   * 使用场合：
   * 张三让链家代其买房
   * 安全控制，控制真实对象被访问时的权限。
   * @param args
   */
  public static void main(String args[]){
    BuyHouse delegateBuyer = new DelegateBuyer();
    LianJiaProxy proxy = new LianJiaProxy(delegateBuyer);
    proxy.buyHouse();
  }
}
