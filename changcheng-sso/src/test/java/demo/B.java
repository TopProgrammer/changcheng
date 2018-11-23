package demo;

public class B extends A {

  public B() {
    System.out.println("hello B");
  }

  {
    System.out.println("I am B class");
  }

  static {
    System.out.println(" static B");
  }

  public static void  main(String args[]){
//    new A();
    System.out.println("--------------------------");
    new B();
  }
}
