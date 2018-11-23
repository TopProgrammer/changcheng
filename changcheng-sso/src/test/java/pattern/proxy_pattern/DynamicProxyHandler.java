package pattern.proxy_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
  // 真是的代理对象
  public Object object;
  public DynamicProxyHandler(final Object proxy) {
    this.object = proxy;
  }

  @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("买房前准备");
    System.out.println("Method:" + method);
    Object result = method.invoke(object, args);
    System.out.println("买房后装修");
    return result;
  }
}
