package site.changcheng.collections;

public class Student implements Comparable<Student>{
  private Integer age;
  private String name;
  private Boolean isMale;
  private Integer No;

  public Student(Integer age, String name, Boolean isMale, Integer no) {
    this.age = age;
    this.name = name;
    this.isMale = isMale;
    No = no;
  }

  public Student() {
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getMale() {
    return isMale;
  }

  public void setMale(Boolean male) {
    isMale = male;
  }

  public Integer getNo() {
    return No;
  }

  public void setNo(Integer no) {
    No = no;
  }


  @Override public int compareTo(Student o) {
    return this.age-o.getAge();
  }
}
