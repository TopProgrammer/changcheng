package lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ForEachTest {
  public static void main(String args[]){
    List<String> list = new ArrayList<>();
    list.add("000");
    list.add("001");
    list.add("002");
    list.add("003");
    list.forEach(s->{
      System.out.println(s);
    });

    Iterator<String> it = list.iterator();
    it.forEachRemaining(s->{
      System.out.println("===="+s);
    });

    System.out.println(calAll(list,s->((String) s).contains("00")));
    System.out.println(calAll(list,s->((String) s).contains("1")));
    System.out.println(calAll(list,s->((String) s).contains("2")));
    System.out.println(calAll(list,s->((String) s).contains("3")));

  }


  public static int calAll(Collection books,Predicate p){
    int total = 0;
    for(Object obj : books){
      if(p.test(obj)){
        total ++ ;
      }
    }
    return total;

  }
}
