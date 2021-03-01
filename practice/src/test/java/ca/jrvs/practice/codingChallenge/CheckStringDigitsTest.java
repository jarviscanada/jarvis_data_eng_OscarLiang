package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckStringDigitsTest {

  CheckStringDigits csd = new CheckStringDigits();

  @Test
  public void checkStringDigitsAscii() {
    assertTrue(csd.checkStringDigitsAscii("12947120"));
    assertFalse(csd.checkStringDigitsAscii("1,525,123"));
    assertFalse(csd.checkStringDigitsAscii("1 232 321"));
    assertFalse(csd.checkStringDigitsAscii("125125a"));
    assertFalse(csd.checkStringDigitsAscii("   1922749"));
    assertTrue(csd.checkStringDigitsAscii(""));
  }

  @Test
  public void checkStringDigitsValueOf() {
    assertTrue(csd.checkStringDigitsValueOf("12947120"));
    assertFalse(csd.checkStringDigitsValueOf("1,525,123"));
    assertFalse(csd.checkStringDigitsValueOf("1 232 321"));
    assertFalse(csd.checkStringDigitsValueOf("125125a"));
    assertFalse(csd.checkStringDigitsValueOf("   1922749"));
    assertTrue(csd.checkStringDigitsValueOf(""));
  }

  @Test
  public void checkStringDigitsRegex() {
    assertTrue(csd.checkStringDigitsRegex("12947120"));
    assertFalse(csd.checkStringDigitsRegex("1,525,123"));
    assertFalse(csd.checkStringDigitsRegex("1 232 321"));
    assertFalse(csd.checkStringDigitsRegex("125125a"));
    assertFalse(csd.checkStringDigitsRegex("   1922749"));
    assertTrue(csd.checkStringDigitsRegex(""));
  }
}