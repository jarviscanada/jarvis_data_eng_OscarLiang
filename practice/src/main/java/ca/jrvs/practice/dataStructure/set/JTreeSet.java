package ca.jrvs.practice.dataStructure.set;

import java.util.TreeMap;

public class JTreeSet<E> implements JSet<E> {

  TreeMap<E, Object> tree;
  private static final Object PRESENT = new Object();

  JTreeSet() {
    tree = new TreeMap<>();
  }

  /**
   * Returns the number of elements in this set (its cardinality). If this set contains more than
   * <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of elements in this set (its cardinality)
   */
  @Override
  public int size() {
    return tree.size();
  }

  /**
   * Returns <tt>true</tt> if this set contains the specified element. More formally, returns
   * <tt>true</tt> if and only if this set contains an element <tt>e</tt> such that
   * <tt>(o==null ? e==null : o.equals(e)</tt>
   *
   * @param o element whose presence in this set is to be tested
   * @return <tt>true</tt> if this set contains the specified element
   * @throws NullPointerException if the specified element is null and this set does not permit null
   *                              elements
   */
  @Override
  public boolean contains(Object o) {
    return tree.containsKey(o);
  }

  /**
   * Adds the specified element to this set if it is not already present (optional operation). More
   * formally, adds the specified element
   * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
   * such that
   * <tt>(e==null ? e2==null : e.equals(e2))</tt>
   * If this set already contains the element, the call leaves the set unchanged and returns
   * <tt>false</tt>. In combination with the restriction on constructors, this ensures that sets
   * never contain duplicate elements
   *
   * @param e element to be added to this set
   * @return <tt>true</tt> if this set did not already contain the specified
   * element
   * @throws NullPointerException if the specified element is null
   */
  @Override
  public boolean add(E e) {
    if (!contains(e)) {
      tree.put(e, PRESENT);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes the specified element from this set if it is present (optional operation). More
   * formally, removes an element <tt>e</tt> such that
   * <tt>(o==null ? e==null : o.equals(e))</tt>, if
   * this set contains such an element. Returns <tt>true</tt> if this set contained the element (or
   * equivalently, if this set changed as a result of the call). (This set will not contain the
   * element once the call returns.)
   *
   * @param o object to be removed from this set, if present
   * @return <tt>true</tt> if this set contained the specified element
   * @throws NullPointerException if the specified element is null and this set does not permit null
   *                              elemnts
   */
  @Override
  public boolean remove(Object o) {
    if (contains(o)) {
      tree.remove(o);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes all of the elements from this set The set will be empty after this call returns.
   */
  @Override
  public void clear() {
    tree.clear();
  }
}
