package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SwapNumbersTest {

  SwapNumbers swapNumbers = new SwapNumbers();

  @Test
  public void swapNumbersBitwise() {
    int[] nums = new int[]{3, 5};
    swapNumbers.swapNumbersBitwise(nums);
    assertArrayEquals(new int[]{5, 3}, nums);

    nums = new int[]{4, 4};
    swapNumbers.swapNumbersBitwise(nums);
    assertArrayEquals(new int[]{4, 4}, nums);
  }

  @Test
  public void swapNumbersArithmetic() {
    int[] nums = new int[]{3, 5};
    swapNumbers.swapNumbersArithmetic(nums);
    assertArrayEquals(new int[]{5, 3}, nums);

    nums = new int[]{4, 4};
    swapNumbers.swapNumbersArithmetic(nums);
    assertArrayEquals(new int[]{4, 4}, nums);
  }
}