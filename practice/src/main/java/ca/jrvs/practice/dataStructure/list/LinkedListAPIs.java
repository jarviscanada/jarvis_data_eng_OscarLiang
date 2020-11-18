package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListAPIs {

  public static void main(String[] args) {
    List<String> animals = new LinkedList<>();

    animals.add("Lion");
    animals.add("Tiger");
    animals.add(2, "Cat");

    int s = animals.size();

    String firstElement = animals.get(0);

    Boolean hasCat = animals.contains("Cat");

    int catIndex = animals.indexOf("Cat");

    boolean isCatRemoved = animals.remove("Cat");
    String removedElement = animals.remove(1);

    animals.sort(String::compareToIgnoreCase);

    System.out.println(Arrays.toString(animals.toArray()));
  }
}
