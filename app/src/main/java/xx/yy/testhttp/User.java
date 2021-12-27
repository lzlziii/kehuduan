package xx.yy.testhttp;

import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 425615203795808487L;

  private Integer id = 1;
  private Integer age = 0;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", age=" + age +
      '}';
  }
}