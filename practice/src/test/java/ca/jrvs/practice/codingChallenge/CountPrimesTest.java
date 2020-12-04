package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountPrimesTest {

  CountPrimes countPrimes = new CountPrimes();

  @Test
  public void countPrimes() {
    assertEquals(3, countPrimes.countPrimes(7));
    assertEquals(25, countPrimes.countPrimes(100));
  }
}