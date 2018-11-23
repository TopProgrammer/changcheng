package memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
  static class OOMObject {

  }

  public static void main(String[] args){
    addIntegerToList();
  }

  public static void addIntegerToList(){
    List<Integer> list = new ArrayList<>();
    for(int i=0;i<200000000;i++){
      list.add(i);
    }
  }
  public static void addObjectToList(){
    List<OOMObject> list = new ArrayList<OOMObject>();
    while(true){
      list.add(new OOMObject());
    }
  }
}
