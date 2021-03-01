package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

  /**
   * find the target index in a sorted array
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */

  public <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target) {

    if (arr.length == 0) {
      return Optional.empty();
    }

    int halfArray = arr.length / 2;
    if (arr[halfArray].compareTo(target) < 0) {
      E[] newArr = Arrays.copyOfRange(arr, halfArray + 1, arr.length);
      Optional<Integer> result = binarySearchRecursion(newArr, target);
      return result.map(integer -> integer + halfArray + 1);
    } else if (arr[halfArray].compareTo(target) > 0) {
      E[] newArr = Arrays.copyOfRange(arr, 0, halfArray);
      return binarySearchRecursion(newArr, target);
    } else {
      return Optional.of(halfArray);
    }
  }

  /**
   * find the target index in a sorted array
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {

    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
      int halfArray = (left + right) / 2;
      if (arr[halfArray].compareTo(target) < 0) {
        left = halfArray + 1;
      } else if (arr[halfArray].compareTo(target) > 0) {
        right = halfArray - 1;
      } else {
        return Optional.of(halfArray);
      }
    }
    return Optional.empty();
  }

}
