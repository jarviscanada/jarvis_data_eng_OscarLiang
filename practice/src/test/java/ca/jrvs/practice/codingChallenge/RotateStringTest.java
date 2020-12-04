package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  RotateString rotator = new RotateString();

  @Test
  public void rotateString() {
    String string1 = "abcde";
    String string2 = "cdeab";
    assertTrue(rotator.rotateString(string1, string2));

    string1 = "abcde";
    string2 = "cdeba";
    assertFalse(rotator.rotateString(string1, string2));

    string1 = "abcde";
    string2 = "abcdea";
    assertFalse(rotator.rotateString(string1, string2));

    string1 = "abcde";
    string2 = "cdeAb";
    assertFalse(rotator.rotateString(string1, string2));
  }
}