package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrintLetterAndNumberTest {

  PrintLetterAndNumber printer = new PrintLetterAndNumber();

  @Test
  public void printLetterAndNumber() {
    assertEquals("a1b2c3e5e5", printer.printLetterAndNumber("abcee"));
    assertEquals("A27O41o15z26d4Z52", printer.printLetterAndNumber("AOozdZ"));
  }
}