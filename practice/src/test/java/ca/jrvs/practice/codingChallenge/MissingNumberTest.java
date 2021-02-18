package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MissingNumberTest {

  MissingNumber missingNumber = new MissingNumber();

  @Test
  public void missingNumberSum() {
    int[] array = new int[]{0};
    assertEquals(1, missingNumber.missingNumberSum(array));
    array = new int[]{3, 5, 0, 7, 1, 6, 2};
    assertEquals(4, missingNumber.missingNumberSum(array));
    array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertEquals(0, missingNumber.missingNumberSum(array));
  }

  @Test
  public void missingNumberSet() {
    int[] array = new int[]{0};
    assertEquals(1, missingNumber.missingNumberSet(array));
    array = new int[]{3, 5, 0, 7, 1, 6, 2};
    assertEquals(4, missingNumber.missingNumberSet(array));
    array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertEquals(0, missingNumber.missingNumberSet(array));
  }

  @Test
  public void missingNumberGauss() {
    int[] array = new int[]{0};
    assertEquals(1, missingNumber.missingNumberGauss(array));
    array = new int[]{3, 5, 0, 7, 1, 6, 2};
    assertEquals(4, missingNumber.missingNumberGauss(array));
    array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertEquals(0, missingNumber.missingNumberGauss(array));
  }
}