package site.changcheng.collections;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionDemo {
  static List<Student> students = new ArrayList<>();
  static Map<String,Student> map = new HashMap();
  static Student[] stuArray= null;
  static {
    Student student1 = new Student(12, "长城", true, 20121001);
    Student student2 = new Student(13, "长", true, 20121001);
    Student student3 = new Student(14, "城", true, 20121001);
    Student student4 = new Student(15, "长城a", true, 20121001);
    Student student5 = new Student(16, "长城b", true, 20121001);
    Student student6 = new Student(17, "长城c", true, 20121001);

    students.add(student2);
    students.add(student4);
    students.add(student3);
    students.add(student6);
    students.add(student5);
    students.add(student1);

    stuArray = new Student[students.size()];
    stuArray[0]=student1;
    stuArray[1]=student2;
    stuArray[2]=student3;
    stuArray[3]=student4;
    stuArray[4]=student5;
    stuArray[5]=student6;

    map.put("1",student1);
    map.put("2",student2);
    map.put("3",student3);
    map.put("4",student4);
    map.put("5",student5);
    map.put("6",student6);
  }

  @Test
  public void sort1() throws Exception {
    Collections.sort(students);

    Iterator<Student> iterator = students.iterator();
    while (iterator.hasNext()) {
      Student student = iterator.next();
      System.out.println(student.getName() + "==" + student.getAge());
    }
  }

  @Test
  public void sort2() throws Exception {
    Comparator<Student> ageCompareable = new Comparator<Student>() {
      @Override public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
      }
    };

    Collections.sort(students, ageCompareable);

    Iterator<Student> iterator = students.iterator();
    while (iterator.hasNext()) {
      Student student = iterator.next();
      System.out.println(student.getName() + "==" + student.getAge());
    }
  }


  @Test
  public void filter() throws Exception {
    List<Student> list = students.stream().filter(s->s.getAge()>15).collect(Collectors.toList());
    Iterator<Student> iterator = list.iterator();
    while (iterator.hasNext()) {
      Student student = iterator.next();
      System.out.println(student.getName() + "==" + student.getAge());
    }
  }

  /**
   * Map 转Set
   * @throws Exception
   */
  @Test
  public void transformMapToSet() throws Exception {

    Set studentSet1 = map.entrySet();
    Set<Student> studentSet2 = new HashSet<>(map.values());

    for(String s:map.keySet()){
      System.out.println(s);
    }
    for(Student s:map.values()){
      System.out.println(JSONObject.toJSONString(s));
    }


    Iterator<Student> it1 = studentSet1.iterator();
    Iterator<Student> it2 = studentSet2.iterator();
    while(it1.hasNext()){
      System.out.println(JSONObject.toJSONString(it1.next()));
    }

    while(it2.hasNext()){
      System.out.println(JSONObject.toJSONString(it2.next()));
    }
  }

  @Test
  public void transformMapToList() throws Exception {
    List<Student> list = new ArrayList<>(map.values());
  }

  /**
   * List 转Array
   * @throws Exception
   */
  @Test
  public void transformListToArray() throws Exception {
    Student[] stu = students.toArray(new Student[students.size()]);
    for(int i=0;i<stu.length;i++){
      System.out.println(JSONObject.toJSONString(stu[i]));
    }
  }

  /**
   * Array转List
   * @throws Exception
   */
  @Test
  public void transformArrayToList() throws Exception {
    List<Student> stuList = Arrays.asList(stuArray);
    Iterator<Student> it1 = stuList.iterator();
    while(it1.hasNext()){
      System.out.println(JSONObject.toJSONString(it1.next()));
    }
  }

  @Test
  public void transformArrayToSet() throws Exception {
    Set<Student> stuSet = new HashSet<>(Arrays.asList(stuArray));
    Iterator<Student> it1 = stuSet.iterator();
    while(it1.hasNext()){
      System.out.println(JSONObject.toJSONString(it1.next()));
    }
  }
}
