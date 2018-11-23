package annotation.test;

import annotation.selfannotation.Notnull;

public class User {

  private String name;
  @Notnull
  private String password;

  private Integer age;
}
