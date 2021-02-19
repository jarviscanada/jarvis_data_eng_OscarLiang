package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import org.junit.Test;

public class DuplicateCharsTest {

  DuplicateChars duplicateChars = new DuplicateChars();

  @Test
  public void findDuplicateChars() {

    List<Character> result = duplicateChars.findDuplicateChars("A black cat");
    assertArrayEquals(new Character[]{'c', 'a'}, result.toArray());

    result = duplicateChars.findDuplicateChars("hi");
    assertArrayEquals(new Character[0], result.toArray());

    result = duplicateChars.findDuplicateChars("Hello, my name is Bob");
    assertArrayEquals(new Character[]{'l', 'm', 'e', 'o'}, result.toArray());
  }
}