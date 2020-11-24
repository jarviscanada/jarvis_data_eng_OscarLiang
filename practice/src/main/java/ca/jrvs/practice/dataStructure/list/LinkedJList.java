package ca.jrvs.practice.dataStructure.list;

public class LinkedJList<E> implements JList<E> {

  private int size = 0;

  Node<E> head = null;

  Node<E> tail = null;

  public LinkedJList() {

  }

  private static class Node<E> {

    E item;
    Node<E> prev;
    Node<E> next;

    Node(E item, Node<E> prev, Node<E> next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  @Override
  public boolean add(E e) {
    addToTail(e);
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node<E> curr = head;
    for (int i = 0; i < size; i++) {
      arr[i] = curr.item;
      curr = curr.next;
    }
    return arr;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(Object o) {
    Node<E> curr = head;
    int index = 0;
    if (o == null) {
      while (curr != null) {
        if (curr.item == null) {
          return index;
        }
        index++;
        curr = curr.next;
      }
    } else {
      while (curr != null) {
        if (o.equals(curr.item)) {
          return index;
        }
        index++;
        curr = curr.next;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public E get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Illegal index " + index + " for current size " + size);
    }
    Node<E> curr = head;
    for (int i = 0; i < index; i++) {
      curr = curr.next;
    }
    return curr.item;
  }

  @Override
  public E remove(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Illegal index " + index + " for current size " + size);
    }
    Node<E> curr;
    if (index == 0) {
      curr = head;
      head = head.next;
    } else if (index == size - 1) {
      curr = tail;
      tail = tail.prev;
    } else {
      curr = head;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }
      curr.prev.next = curr.next;
      curr.next.prev = curr.prev;
    }
    E result = curr.item;
    curr.item = null;
    curr.next = null;
    curr.prev = null;
    size--;
    return result;
  }

  @Override
  public void clear() {
    // Clear all nodes, just to be safe
    Node<E> curr = head;
    while (curr != null) {
      Node<E> temp = curr.next;
      curr.prev = null;
      curr.item = null;
      curr.next = null;
      curr = temp;
    }
    head = null;
    tail = null;
    size = 0;
  }

  public void addToHead(E e) {
    Node<E> elem = new Node(e, null, head);
    if (tail == null) {
      tail = elem;
    } else {
      head.prev = elem;
    }
    head = elem;
    size++;
  }

  public void addToTail(E e) {
    Node<E> elem = new Node(e, tail, null);
    if (head == null) {
      head = elem;
    } else {
      tail.next = elem;
    }
    tail = elem;
    size++;
  }
}
