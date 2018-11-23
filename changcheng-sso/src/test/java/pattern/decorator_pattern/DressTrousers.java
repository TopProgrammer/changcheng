package pattern.decorator_pattern;

public class DressTrousers implements DressCloths{
  @Override public DecoratorPerson dress(DecoratorPerson person) {
    System.out.println("穿了个裤子");
    return person;
  }
}
