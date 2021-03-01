package ca.jrvs.practice.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearch binarySearch = new BinarySearch();

  @Test
  public void binarySearchRecursion() {
    Integer[] myArray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    Optional<Integer> result;
    for (Integer i = 0; i < myArray.length; i++) {
      result = binarySearch.binarySearchRecursion(myArray, i);
      assertTrue(result.isPresent());
      assertEquals(i, result.get());
    }
    myArray = new Integer[]{2, 5, 6, 9, 10, 15};
    result = binarySearch.binarySearchRecursion(myArray, 10);
    assertTrue(result.isPresent());
    assertEquals(4, (int) result.get());
    result = binarySearch.binarySearchRecursion(myArray, 4);
    assertFalse(result.isPresent());
  }

  @Test
  public void binarySearchIteration() {
    Integer[] myArray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    Optional<Integer> result;
    for (Integer i = 0; i < myArray.length; i++) {
      result = binarySearch.binarySearchIteration(myArray, i);
      assertTrue(result.isPresent());
      assertEquals(i, result.get());
    }
    myArray = new Integer[]{2, 5, 6, 9, 10, 15};
    result = binarySearch.binarySearchIteration(myArray, 10);
    assertTrue(result.isPresent());
    assertEquals(4, (int) result.get());
    result = binarySearch.binarySearchIteration(myArray, 4);
    assertFalse(result.isPresent());
  }
}