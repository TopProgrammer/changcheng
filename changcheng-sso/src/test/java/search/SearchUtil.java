package search;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SearchUtil {
  @Test
  public void testErfen() throws Exception {
    Integer[] intArrs = {1,3,5,7,9,11,24,45,56,57,66,67,69};
    List<Integer> arrs = Arrays.asList(intArrs);
    System.out.println(erFenSearch(arrs,67,0,arrs.size()-1));
  }

  public int erFenSearch(List<Integer> arrs,int target,int startPos,int endPos){

    int min = arrs.get(startPos);
    int length = endPos-startPos;
    int middleIndex = startPos+(length%2==0? (length/2):(length+1)/2);
    int middle = arrs.get(middleIndex);
    int max = arrs.get(endPos);

    if(target>max||target<min){
      return -1;
    }

    if(middle ==target){
      return middleIndex;
    }else if(min == target){
      return 0;
    }else if(max == target){
      return length-1;
    }

    if(middle>target&&min<target){
      return erFenSearch(arrs,target,startPos,middleIndex);
    }else if(middle<target&&max>target){
      return erFenSearch(arrs,target,middleIndex,endPos);
    }
    return -1;
  }
}
