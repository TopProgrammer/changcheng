package demo;

import org.junit.Test;

public class TestDemo {
  public static void main(String argsp[]) throws InterruptedException {
    Thread s= new Thread(){
      public void run(){

        pong();
      }
    };
    s.start();
    System.out.println("ping");
    Thread.sleep(1l);
    Object o;
  }

  @Test
  public void testBreak() throws Exception {

    for(int i=0;i<10;i++){
      for(int j=0;j<10;j++){
        System.out.println("--j="+j);
        if(j==5){
          System.out.println("-------j="+j);
          break;
//          continue;
        }
      }
      System.out.println("-----"+i);
    }
  }

  @Test
  public void testMergeNode() throws Exception {

    Node node10 = new Node(10);
    Node node9 = new Node(9);
    Node node8 = new Node(node10,8);
    Node node7 = new Node(node9,7);
    Node node6 = new Node(node8,6);
    Node node5 = new Node(node7,5);
    Node node4 = new Node(node6,4);
    Node node3 = new Node(node5,3);
    Node node2 = new Node(node4,2);
    Node nodeB = new Node(node3,3);
    Node nodeA = new Node(node2,0);

    mergeNode(nodeA,nodeB);
    while(nodeA.hasNext()){

      System.out.println("========");
      System.out.println(nodeA.next.value);
//      System.out.println(nodeB.next.value);
      nodeA = nodeA.next;

    }

  }

  public void mergeNode(Node a,Node b){

    while(a.hasNext()){
      while(b.hasNext()){
        Node aNext = a.next;
        Node bNext = b.next;
        if(b.compareTo(a)>=0){
          if(b.compareTo(aNext)>0){
            a = aNext;
            continue;
          }
          a.next = b;
          b.next = aNext;
          b = bNext;

          continue;
        }


        // b<a<bNext
        // bNext>a
        if(bNext.compareTo(a)>0){

          if(bNext.compareTo(a.next)>0){
            b = b.next;
            continue;
          }
          a.next = bNext;
          b.next = a;

        }
      }

    }




  }

  class Node implements Comparable{
    Node next;
    Integer value;
    public Node(Integer value){
      this.value = value;
    }
    public Node(Node next,Integer value){
      this.next = next;
      this.value = value;
    }

    @Override public int compareTo(Object o) {
      if(o instanceof Node){
        int value = ((Node) o).value;
        return this.value-value;
      }
      return -1;
    }

    public boolean hasNext(){
      return  this.next !=null;
    }
  }

  static void pong(){
    System.out.println("pong");
  }
}
