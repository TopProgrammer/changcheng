package pattern.proxy_pattern;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
  private Object object;
  public Object getInstance(final Object target){
    this.object = target;
    Enhancer enhance = new Enhancer();

    enhance.setSuperclass(this.object.getClass());
    enhance.setCallback(this);
    ThreadLocal local = new ThreadLocal();
    return enhance.create();


  }
  @Override public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("买房前准备");
    System.out.println("Method:" + method);
    Object result = method.invoke(object, args);
    System.out.println("买房后装修");
    return result;
  }
}
