package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateChars {

  public List<Character> findDuplicateChars(String str) {

    Set<Character> charSet = new HashSet<>();
    Set<Character> duplicateCheck = new HashSet<>();
    List<Character> duplicates = new ArrayList<>();

    for (int i = 0; i < str.length(); i++) {
      char currentChar = str.charAt(i);

      // Ignore spaces
      if (currentChar != ' ') {
        if (charSet.contains(currentChar)) {
          if (!duplicateCheck.contains(currentChar)) {
            duplicateCheck.add(currentChar);
            duplicates.add(currentChar);
          }
        } else {
          charSet.add(currentChar);
        }
      }
    }

    return duplicates;
  }

}
