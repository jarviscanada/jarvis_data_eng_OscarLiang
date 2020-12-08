package ca.jrvs.practice.dataStructure.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Employee {

  private int id;
  private String name;
  private int age;
  private long salary;

  public static void main(String[] args) {
    HashMap<Employee, List<String>> prevEmployers = new HashMap<>();
    Employee alice = new Employee(1, "Alice", 25, 100000);
    List<String> aliceEmployers = new ArrayList<>();
    aliceEmployers.add("Apple");
    aliceEmployers.add("Google");
    aliceEmployers.add("Microsoft");
    prevEmployers.put(alice, aliceEmployers);

    Employee bob = new Employee(2, "Bob", 37, 80000);
    List<String> bobEmployers = new ArrayList<>();
    bobEmployers.add("Amazon");
    bobEmployers.add("Facebook");
    prevEmployers.put(bob, bobEmployers);

    List<String> someEmployers = prevEmployers.get(alice);
    someEmployers.stream().forEach(System.out::println);
  }

  public Employee() {
  }

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return id == employee.id &&
        age == employee.age &&
        salary == employee.salary &&
        Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary);
  }
}
