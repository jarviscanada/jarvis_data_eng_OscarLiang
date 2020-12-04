package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Count-Primes-8c0e7a0e842e43d0b975c1a5e4e7f28e
 */
public class CountPrimes {

  /**
   * Time complexity: O(n log log n) https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_and_variants
   * Space complexity: O(n) - uses a size n array of booleans
   */
  public int countPrimes(int n) {
    boolean[] sieve = new boolean[n];

    for (int i = 2; i < n; i++) {
      sieve[i] = true;
    }

    for (int i = 2; i * i < n; i++) {
      if (sieve[i]) {
        for (int j = i * i; j < n; j += i) {
          sieve[j] = false;
        }
      }
    }

    int numPrimes = 0;
    for (int i = 2; i < n; i++) {
      if (sieve[i]) {
        numPrimes++;
      }
    }
    return numPrimes;
  }

}
