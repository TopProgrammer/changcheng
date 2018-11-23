package lambda;

import com.github.pagehelper.PageHelper;

import javax.swing.*;

public class MethodRef {

  public static void main(String args[]){
    Converter c =form->Integer.valueOf(form);
    c = Integer::valueOf;
    Integer va1 = c.convert("99");
    System.out.println(va1);

    Converter r1 = form -> {
      return Integer.valueOf(form);
    };

    System.out.println(r1.convert("100"));
    Converter r2 = form->"test".indexOf(form);
    r2 = "test"::indexOf;
    System.out.println(r2.convert("t"));

    MyTest t = (a,b,d)->{
      return a+b+d;
    };

    YourTest yt = a->new JFrame();
    YourTest yt2 = (String a)->new JFrame(a);
  }
}

@FunctionalInterface
interface Converter{
  Integer convert(String form);
}

@FunctionalInterface
interface MyTest{
  String test(String a,String b,String c);
}

@FunctionalInterface
interface YourTest{
  JFrame win(String title);
}