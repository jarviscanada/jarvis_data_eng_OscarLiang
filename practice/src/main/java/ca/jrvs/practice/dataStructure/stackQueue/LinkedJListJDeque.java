package ca.jrvs.practice.dataStructure.stackQueue;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import java.util.NoSuchElementException;

public class LinkedJListJDeque<E> implements JDeque<E> {

  LinkedJList<E> deque;

  public LinkedJListJDeque() {
    deque = new LinkedJList<>();
  }

  @Override
  public boolean add(E e) {
    deque.add(e);
    return true;
  }

  @Override
  public E remove() {
    try {
      return deque.remove(0);
    } catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
  }

  @Override
  public E pop() {
    return remove();
  }

  @Override
  public void push(E e) {
    deque.addToHead(e);
  }

  @Override
  public E peek() {
    return deque.get(0);
  }
}
