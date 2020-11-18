package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class OddEvenTest {

  @Test
  public void oddEvenMod() {
    OddEven oe = new OddEven();
    Assert.assertEquals(oe.oddEvenMod(0), "even");
    Assert.assertEquals(oe.oddEvenMod(1), "odd");
    Assert.assertEquals(oe.oddEvenMod(29329), "odd");
    Assert.assertEquals(oe.oddEvenMod(19232), "even");
  }

  @Test
  public void obbEvenBit() {
    OddEven oe = new OddEven();
    Assert.assertEquals(oe.oddEvenBit(0), "even");
    Assert.assertEquals(oe.oddEvenBit(1), "odd");
    Assert.assertEquals(oe.oddEvenBit(29329), "odd");
    Assert.assertEquals(oe.oddEvenBit(19232), "even");
  }
}