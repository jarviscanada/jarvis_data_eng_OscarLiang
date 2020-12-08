package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {

  @Test
  public void fibonacciRecursion() {
    Fibonacci fibonacci = new Fibonacci();
    Assert.assertEquals(89, fibonacci.fibonacciRecursion(11));
    Assert.assertEquals(1, fibonacci.fibonacciRecursion(1));
    Assert.assertEquals(13, fibonacci.fibonacciRecursion(7));
  }

  @Test
  public void fibonacciDynamic() {
    Fibonacci fibonacci = new Fibonacci();
    Assert.assertEquals(89, fibonacci.fibonacciDynamic(11));
    Assert.assertEquals(1, fibonacci.fibonacciDynamic(1));
    Assert.assertEquals(13, fibonacci.fibonacciDynamic(7));
  }
}