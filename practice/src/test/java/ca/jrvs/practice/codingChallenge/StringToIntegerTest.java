package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringToIntegerTest {

  StringToInteger stringToInteger = new StringToInteger();

  @Test
  public void stringToIntegerBuiltIn() {
    assertEquals(42, stringToInteger.stringToIntegerBuiltIn("42"));
    assertEquals(-42, stringToInteger.stringToIntegerBuiltIn("-42"));
    assertEquals(-42, stringToInteger.stringToIntegerManual("       -42"));
    assertEquals(345, stringToInteger.stringToIntegerManual("345 123 hahaha"));
  }

  @Test
  public void stringToIntegerManual() {
    assertEquals(42, stringToInteger.stringToIntegerManual("42"));
    assertEquals(-42, stringToInteger.stringToIntegerManual("-42"));
    assertEquals(3, stringToInteger.stringToIntegerManual("3.14159"));
    assertEquals(-42, stringToInteger.stringToIntegerManual("       -42"));
    assertEquals(0, stringToInteger.stringToIntegerManual(""));
    assertEquals(Integer.MAX_VALUE, stringToInteger.stringToIntegerManual("102975102975112"));
    assertEquals(Integer.MIN_VALUE, stringToInteger.stringToIntegerManual("-102491759217512"));
    assertEquals(345, stringToInteger.stringToIntegerManual("345 123 hahaha"));
  }
}