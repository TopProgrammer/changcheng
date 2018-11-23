package lambda;

public class LamadaQs {
  public void eat(Eatable e){
    System.out.println(e);
    e.taste();
  }

  public void drive(Flyable f){
    System.out.println("我正在驾驶:"+f);
    f.fly("碧空如洗的晴日");
  }

  public void add(Addable add){
    add.add(6,7);
    //    System.out.println("5+3="+add.add(3,5));
  }

  public static void main(String args[]){
    LamadaQs lq = new LamadaQs();
    lq.eat(new Eatable() {
      @Override public void taste() {
        System.out.println(" 吃辣啦吃辣啦吃辣啦");
      }
    });

    lq.eat(()->{
      System.out.println("--------------->吃啦啦啦啦啦啦啦啦啦");
    });

    lq.drive(os->{
      System.out.println("-------drive---------------------os");
    });

    lq.add((a,b)->a+b);
    lq.add(new Addable() {
      @Override public int add(int a, int b) {
        return a+b;
      }
    });

    Runnable r = new Runnable() {
      @Override public void run() {
        System.out.println("=====================");

      }
    };


    Runnable r1 = ()-> {
        System.out.println("=====================");
      };

  }
}
@FunctionalInterface
interface Eatable{
  void taste();
}
@FunctionalInterface
interface  Flyable{
  void fly(String weather);
}

@FunctionalInterface
interface Addable{
  int add(int a,int b);
}

