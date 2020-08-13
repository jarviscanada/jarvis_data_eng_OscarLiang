package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.LambdaStreamExc;
import ca.jrvs.apps.practice.LambdaStreamExcImp;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

public class LambdaStreamTests {

  @Test
  public void createStrStreamTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    MatcherAssert.assertThat("Returned object was not a Stream",
        l.createStrStream("Test", "Strings", "Here"),
        CoreMatchers.instanceOf(Stream.class));
  }

  @Test
  public void validateStrStream() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Stream<String> stringStream = l.createStrStream("Test", "Strings", "Here");
    String[] strings = {"Test", "Strings", "Here"};
    Iterator<String> it = stringStream.iterator();
    int arrayIndex = 0;
    try {
      while (it.hasNext()) {
        Assert.assertEquals("Strings do not match", strings[arrayIndex], it.next());
        arrayIndex++;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      Assert.fail("More strings than expected");
    }
    if (arrayIndex < strings.length) {
      Assert.fail("Fewer strings than expected");
    }
  }

  @Test
  public void toUpperCaseTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Stream<String> stringStream = l.toUpperCase("Test", "Strings", "Here");
    stringStream.forEach(str -> {
      Assert.assertEquals("Expected string in upper case", str.toUpperCase(), str);
    });
  }

  @Test
  public void filterSome() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Stream<String> stringStream = l
        .createStrStream("Green", "Blue", "Black", "Brown", "Red", "Sky Blue");
    stringStream = l.filter(stringStream, "Bl");
    Assert.assertEquals("Incorrect number of elements in stream", 3, stringStream.count());
  }

  @Test
  public void filterAll() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Stream<String> stringStream = l.createStrStream("Hello", "Goodbye", "The Beatles");
    stringStream = l.filter(stringStream, "e");
    Assert.assertEquals("Some elements not filtered", 0, stringStream.count());
  }

  @Test
  public void filterNone() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Stream<String> stringStream = l.createStrStream("My", "Name", "Is", "Oscar");
    stringStream = l.filter(stringStream, "ZZZ");
    Assert.assertEquals("Some elements filtered", 4, stringStream.count());
  }

  @Test
  public void createIntStreamTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    int[] arr = {9, 3, 1, 5, 6, 2};
    MatcherAssert.assertThat("Returned object was not an IntStream", l.createIntStream(arr),
        CoreMatchers.instanceOf(IntStream.class));
  }

  @Test
  public void validateIntStream() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    int[] arr = {1, 2, 3, 4, 5};
    IntStream intStream = l.createIntStream(arr);
    Iterator<Integer> it = intStream.iterator();
    int n = 1;
    while (it.hasNext()) {
      Assert.assertEquals("inStream does not match value", n, (int) it.next());
      n++;
    }
    Assert.assertEquals("Unexpected number of elements in intStream", 5, n - 1);
  }

  @Test
  public void createRangeIntStreamTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    IntStream intStream = l.createIntStream(1, 20);
    MatcherAssert.assertThat("Returned object was not an intStream", l.createIntStream(1, 10),
        CoreMatchers.instanceOf(IntStream.class));
  }

  @Test
  public void validateRangeIntStream() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    IntStream intStream = l.createIntStream(1, 20);
    Iterator<Integer> it = intStream.iterator();
    int n = 1;
    while (it.hasNext()) {
      Assert.assertEquals("inStream does not match value", n, (int) it.next());
      n++;
    }
    Assert.assertEquals("Unexpected number of elements in intStream", 20, n - 1);
  }

  @Test
  public void squareRootsTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    int[] arr = {1, 4, 9, 16, 25};
    IntStream intStream = l.createIntStream(arr);
    DoubleStream doubleStream = l.squareRootIntStream(intStream);
    Iterator<Double> it = doubleStream.iterator();
    int n = 1;
    while (it.hasNext()) {
      Assert.assertEquals("Square root does not match expected value", n, it.next(), 0.01);
      n++;
    }
    Assert.assertEquals("Unexpected number of stream elements", 5, n - 1);
  }

  @Test
  public void oddNumbersNoneFilteredTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    int[] arr = {1, 3, 5, 7, 9};
    IntStream intStream = l.createIntStream(arr);
    intStream = l.getOdd(intStream);
    Assert.assertEquals("Some numbers got filtered", 5, intStream.count());
  }

  @Test
  public void oddNumbersAllFilteredTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    int[] arr = {2, 4, 6, 8, 10};
    IntStream intStream = l.createIntStream(arr);
    intStream = l.getOdd(intStream);
    Assert.assertEquals("Some numbers did not get filtered", 0, intStream.count());
  }

  @Test
  public void oddNumberSomeFilteredTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    IntStream intStream = l.createIntStream(1, 10);
    intStream = l.getOdd(intStream);
    Assert.assertEquals("Unexpected number of elements", 5, intStream.count());
  }

  @Test
  public void printerTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Consumer<String> consumer = l.getLambdaPrinter("start>", "<end");
    // Redirect console output to string variable for test
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    consumer.accept("Hello");
    // Reset to console output
    System.out.flush();
    System.setOut(old);
    Assert.assertEquals("Unexpected message output", "start>Hello<end\n", baos.toString());
  }

  @Test
  public void printMessagesTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Consumer<String> consumer = l.getLambdaPrinter("msg:", "!");
    String[] messages = {"a", "b", "c"};
    // Redirect console output to string variable for test
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    l.printMessages(messages, consumer);
    // Reset to console output
    System.out.flush();
    System.setOut(old);
    Assert.assertEquals("Unexpected message output", "msg:a!\nmsg:b!\nmsg:c!\n", baos.toString());
  }

  @Test
  public void printOddTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    Consumer<String> consumer = l.getLambdaPrinter("msg:", "!");
    IntStream intStream = l.createIntStream(1, 5);
    // Redirect console output to string variable for test
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    l.printOdd(intStream, consumer);
    // Reset to console output
    System.out.flush();
    System.setOut(old);
    Assert.assertEquals("Unexpected message output", "msg:1!\nmsg:3!\nmsg:5!\n", baos.toString());
  }

  @Test
  public void flatMapTest() {
    LambdaStreamExc l = new LambdaStreamExcImp();
    List<List<Integer>> lists = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Integer> list = new ArrayList<>();
      list.add(i);
      lists.add(list);
    }
    Stream<Integer> intStream = l.flatNestedInt(lists.stream());
    int n = 0;
    Iterator<Integer> it = intStream.iterator();
    while (it.hasNext()) {
      Assert.assertEquals("Unexpected squared value", n * n, (int) it.next());
      n++;
    }
    Assert.assertEquals("Unexpected number of stream elements", n, 5);
  }
}
