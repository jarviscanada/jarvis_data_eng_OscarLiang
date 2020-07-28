package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LambdaStreamExc {

  /**
   * Create a String stream from array
   *
   * @param strings
   * @return String stream
   */
  Stream<String> createStrStream(String... strings);

  /**
   * Convert all strings to uppercase
   *
   * @param strings
   * @return String stream with all uppercase strings
   */
  Stream<String> toUpperCase(String... strings);

  /**
   * filter strings that contain the pattern
   *
   * @param stringStream
   * @param pattern
   * @return String stream where all strings do not contain the given pattern
   */
  Stream<String> filter(Stream<String> stringStream, String pattern);

  /**
   * Create an intStream from an arr[]
   *
   * @param arr
   * @return
   */
  IntStream createIntStream(int[] arr);

  /**
   * Convert a stream to list
   *
   * @param stream
   * @param <E>
   * @return
   */
  <E> List<E> toList(Stream<E> stream);

  /**
   * Convert an intStream to list
   *
   * @param intStream
   * @return
   */
  List<Integer> toList(IntStream intStream);

  /**
   * Create an IntStream from start to end inclusive
   *
   * @param start
   * @param end
   * @return
   */
  IntStream createIntStream(int start, int end);

  /**
   * Convert an intStream to a doubleStream and compute square root of each element
   *
   * @param intStream
   * @return
   */
  DoubleStream squareRootIntStream(IntStream intStream);

  /**
   * Filter all even numbers and return odd numbers from an intStream
   *
   * @param intStream
   * @return
   */
  IntStream getOdd(IntStream intStream);

  /**
   * Return a lambda function that prints a message with a prefix and suffix This lambda can be
   * useful to format logs.
   *
   * @param prefix prefix string
   * @param suffix suffix string
   * @return
   */
  Consumer<String> getLambdaPrinter(String prefix, String suffix);

  /**
   * Print each message with a given printer
   *
   * @param messages
   * @param printer
   */
  void printMessages(String[] messages, Consumer<String> printer);

  /**
   * Print all odd numbers from an intStream
   *
   * @param intStream
   * @param printer
   */
  void printOdd(IntStream intStream, Consumer<String> printer);

  /**
   * Square each number from the input.
   *
   * @param ints
   * @return
   */
}
