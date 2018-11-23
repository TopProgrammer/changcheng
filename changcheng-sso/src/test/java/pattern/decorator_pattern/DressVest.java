package pattern.decorator_pattern;

public class DressVest implements DressCloths{
  @Override public DecoratorPerson dress(DecoratorPerson person) {
    System.out.println("穿了个背心");
    return person;
  }
}
