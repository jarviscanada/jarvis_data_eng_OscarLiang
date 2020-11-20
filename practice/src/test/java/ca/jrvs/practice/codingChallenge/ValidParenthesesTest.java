package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidParenthesesTest {

  @Test
  public void isValid() {
    ValidParentheses vp = new ValidParentheses();
    assertTrue(vp.isValid("()"));
    assertTrue(vp.isValid("()[]{}"));
    assertFalse(vp.isValid("(]"));
    assertFalse(vp.isValid("([)]"));
    assertTrue(vp.isValid("{[]}"));
    assertFalse(vp.isValid("{"));
    assertTrue(vp.isValid(""));
  }
}