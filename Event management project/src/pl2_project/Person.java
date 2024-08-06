/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2_project;

import java.io.IOException;
import static pl2_project.fileHandling.readFile;

/**
 *
 * @author Mohamed Hassan
 */
public abstract class Person {
  protected String name;
  protected String email;
  protected String password;
  protected String phone;
  protected String role;
  public static int count;
  protected String id;

  public Person(String name, String email, String password, String phone, String role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.role = role;
    Person.count++;
    this.id = count + "";
  }

  Person() {
      
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void objectifyPerson(int id, String fileName, Person p) throws IOException {
    String f = readFile(fileName);
    String[] users = f.split("\n");
    for (int i = 0; i < users.length; i++) {
      if (users[i].startsWith(id + "")) {
        String userInfo[] = users[i].split(",");
        p.id = userInfo[0];
        p.name = userInfo[1];
        p.email = userInfo[2];
        p.password = userInfo[3];
        p.phone = userInfo[4];
        p.role = userInfo[5];
      }
    }

  }

  @Override
  public String toString() {
    return "Person{ " + "name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", role="
        + role + '}';
  }

}
