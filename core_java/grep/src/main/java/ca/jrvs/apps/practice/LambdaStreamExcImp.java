package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    Stream<String> stringStream = createStrStream(strings);
    return stringStream.map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    // Add ".*" to start and end of pattern to fully match any string containing the pattern
    String regex = ".*" + pattern + ".*";
    return stringStream.filter(str -> {
      return !Pattern.compile(regex).matcher(str).matches();
    });
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(num -> {
      return Math.sqrt(num);
    });
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(number -> {
      return number % 2 == 1;
    });
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    Consumer<String> consumer = (String message) -> {
      System.out.println(prefix + message + suffix);
    };
    return consumer;
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    for (String m : messages) {
      printer.accept(m);
    }
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    getOdd(intStream).forEach(n -> {
      printer.accept(((Integer) n).toString());
    });
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(list -> list.stream().map(n -> n * n));
  }
}
