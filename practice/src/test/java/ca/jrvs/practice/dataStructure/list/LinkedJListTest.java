package ca.jrvs.practice.dataStructure.list;

import org.junit.Assert;
import org.junit.Test;

public class LinkedJListTest {

  @Test
  public void add() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    Assert.assertEquals("Hello", list.get(0));
  }

  @Test
  public void toArray() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    list.add("Goodbye");
    String[] arr = {"Hello", "Goodbye"};
    Assert.assertArrayEquals(arr, list.toArray());
  }

  @Test
  public void size() {
    JList<String> list = new LinkedJList<>();
    Assert.assertEquals(0, list.size());
    list.add("Hello");
    list.add("my");
    list.add("name");
    list.add("is");
    list.add("Oscar");
    Assert.assertEquals(5, list.size());
    list.remove(3);
    Assert.assertEquals(4, list.size());
  }

  @Test
  public void isEmpty() {
    JList<String> list = new LinkedJList<>();
    Assert.assertTrue(list.isEmpty());
    list.add("Hello");
    Assert.assertFalse(list.isEmpty());
  }

  @Test
  public void indexOf() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    Assert.assertEquals(0, list.indexOf("Hello"));
    Assert.assertEquals(-1, list.indexOf("Goodbye"));
  }

  @Test
  public void contains() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    list.add("Goodbye");
    list.add("The Beatles");
    Assert.assertTrue(list.contains("Hello"));
    Assert.assertFalse(list.contains("hello"));
  }

  @Test
  public void get() {
    JList<String> list = new LinkedJList<>();
    list.add("Hi");
    list.add("Hello");
    Assert.assertEquals("Hello", list.get(1));
  }

  @Test
  public void remove() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    list.add("Goodbye");
    list.add("Hi");
    list.add("Bye");
    list.add("Good night");
    list.add("Hey");
    Assert.assertEquals("Hello", list.remove(0));
    Assert.assertEquals("Hey", list.remove(4));
    Assert.assertEquals("Hi", list.remove(1));
    Assert.assertEquals("Bye", list.remove(1));
  }

  @Test
  public void clear() {
    JList<String> list = new LinkedJList<>();
    list.add("Hello");
    list.add("Goodbye");
    list.add("The Beatles");
    list.clear();
    Assert.assertEquals(0, list.size());
    Assert.assertFalse(list.contains("Hello"));
  }
}
