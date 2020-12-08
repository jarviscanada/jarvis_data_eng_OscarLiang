package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnagramCheckerTest {

  @Test
  public void isAnagramSort() {
    AnagramChecker anagramChecker = new AnagramChecker();
    assertTrue(anagramChecker.isAnagramSort("oscarliang", "singacarol"));
    assertFalse(anagramChecker.isAnagramSort("not", "anagram"));
    assertFalse(anagramChecker.isAnagramSort("javacode", "javadocs"));
  }

  @Test
  public void isAnagramLinear() {
    AnagramChecker anagramChecker = new AnagramChecker();
    assertTrue(anagramChecker.isAnagramLinear("oscarliang", "singacarol"));
    assertFalse(anagramChecker.isAnagramLinear("not", "anagram"));
    assertFalse(anagramChecker.isAnagramLinear("javacode", "javadocs"));
  }
}