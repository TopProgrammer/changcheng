package site.changcheng;

import java.util.Stack;

/**
 *
 */
public class DoubleStackToQueue {

  public Stack<String> s1 = new Stack<>();
  public Stack<String> s2 = new Stack<>();

  public void push(String s){
    s1.push(s);
  }

  public String pop(){
    for(int i=0;i<s2.size();i++){
      s2.pop();
    }
    if(s2.isEmpty()){
      for(int i=0;i<s1.size();i++){
        s2.push(s1.pop());
      }
    }
    return s2.pop();
  }
}
