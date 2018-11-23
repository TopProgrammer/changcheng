package pattern.decorator_pattern;

public class DecoratorMain {
  public static void main(String args[]){
    DecoratorPerson person = new DecoratorPerson();
    DressVest vest = new DressVest();
    DressTrousers trousers = new DressTrousers();

    vest.dress(person);
    trousers.dress(person);
  }
}
