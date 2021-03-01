package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import org.junit.Test;

public class RemoveElementTest {

  RemoveElement removeElement = new RemoveElement();

  @Test
  public void removeElement() {
    int[] testArray = new int[]{3, 1, 6, 23, 1, 5, 3, 7, 1};
    int newLength = removeElement.removeElement(testArray, 1);
    int[] resultArray = new int[newLength];
    System.arraycopy(testArray, 0, resultArray, 0, newLength);
    Arrays.sort(resultArray);
    assertArrayEquals(new int[]{3, 3, 5, 6, 7, 23}, resultArray);

    testArray = new int[]{1, 2, 3, 4, 5, 6, 7};
    removeElement.removeElement(testArray, 8);
    resultArray = new int[]{1, 2, 3, 4, 5, 6, 7};
    assertArrayEquals(testArray, resultArray);
  }
}