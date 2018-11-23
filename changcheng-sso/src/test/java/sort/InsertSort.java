package sort;

import org.junit.Test;

public class InsertSort {
  @Test
  public void insertSort() throws Exception {

  }


  public void sort(int arr[]){
    int sorted= 1;


    for(int i=0;i<arr.length;i++){
      if(arr[i]>=arr[sorted]){

      }else if(arr[i]<arr[sorted]){
        sort1(arr,i,sorted);
      }
    }










  }


  public void sort1(int arr[] ,int currentPos,int sorted){

    for(int i=sorted;i>0;i--){

      if(arr[currentPos]<arr[i]&&arr[currentPos]>=arr[i-1]){
        int pre =arr[i];
        arr[i]=arr[currentPos];
        int temp = 0;
        for(int index = i+1;index <currentPos;index ++){
          temp = arr[index];
          arr[index] = pre;
          pre = temp;
        }
      }
    }

  }
}
