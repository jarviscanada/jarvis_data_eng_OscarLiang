package ca.jrvs.practice.dataStructure.tree;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map Comparator cannot be null
   */
  private Comparator<E> comparator;
  private Node<E> root;

  public static void main(String[] args) {
    JTree<Integer> jTree = new JBSTree<>(Comparator.comparingInt(i -> i));
    jTree.insert(30);
    jTree.insert(15);
    jTree.insert(45);
    jTree.insert(7);
    jTree.insert(22);
    jTree.insert(37);
    jTree.insert(55);
    jTree.insert(4);
    jTree.insert(12);
    jTree.insert(18);
    jTree.insert(26);
    jTree.insert(50);
    jTree.insert(60);
    jTree.insert(17);
    jTree.insert(20);
    jTree.insert(24);
    jTree.insert(28);
    jTree.insert(29);

    Object[] intArray = jTree.inOrder();
    for (Object i : intArray) {
      System.out.println(i);
    }

    System.out.println(jTree.findHeight());
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return Objects.equals(value, node.value) &&
          Objects.equals(left, node.left) &&
          Objects.equals(right, node.right) &&
          Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, left, right, parent);
    }
  }

  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    if (comparator == null) {
      throw new IllegalArgumentException("Cannot have null comparator");
    }
    root = null;
    this.comparator = comparator;
  }

  /**
   * Insert an object into the tree.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (root == null) {
      root = new Node<E>(object, null);
      return object;
    }
    Node<E> curr = root;
    while (true) {
      int result = comparator.compare(curr.getValue(), object);
      if (result == 0) {
        throw new IllegalArgumentException("object already exists");
      } else if (result > 0) {
        if (curr.getLeft() == null) {
          curr.setLeft(new Node(object, curr));
          return object;
        } else {
          curr = curr.getLeft();
        }
      } else { // result < 0
        if (curr.getRight() == null) {
          curr.setRight(new Node<E>(object, curr));
          return object;
        } else {
          curr = curr.getRight();
        }
      }
    }
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    Node<E> curr = root;
    if (object == null) {
      return null;
    }
    while (curr != null) {
      int result = comparator.compare(curr.getValue(), object);
      if (result == 0) {
        return curr.getValue();
      } else if (result > 0) {
        curr = curr.getLeft();
      } else { // result < 0
        curr = curr.getRight();
      }
    }
    return null;
  }

  /**
   * Remove an object from the tree
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object does not exist
   */
  @Override
  public E remove(E object) {
    if (object == null) {
      return null;
    }
    Node<E> curr = root;
    while (curr != null) {
      int result = comparator.compare(curr.getValue(), object);
      if (result == 0) {
        if (curr.equals(root)) {
          if (curr.getRight() == null) {
            root = curr.getLeft();
          } else {
            root = curr.getRight();
            // Move the left branch to the leftmost leaf of the right branch
            if (curr.getLeft() != null) {
              Node<E> leftBranch = root;
              while (leftBranch.getLeft() != null) {
                leftBranch = leftBranch.getLeft();
              }
              leftBranch.setLeft(curr.getLeft());
            }
          }
          root.setParent(null);
        } else {
          Node<E> parent = curr.getParent();
          // Check whether this node is its parent's left or right
          result = comparator.compare(parent.getValue(), curr.getValue());
          Node<E> newBranch; // Build a new branch from children of the removed node
          if (curr.getRight() != null) {
            newBranch = curr.getRight();
            // Move the left branch to the leftmost leaf of the right branch
            if (curr.getLeft() != null) {
              Node<E> leftBranch = newBranch;
              while (leftBranch.getLeft() != null) {
                leftBranch = leftBranch.getLeft();
              }
              leftBranch.setLeft(curr.getLeft());
            }
          } else if (curr.getLeft() != null) {
            newBranch = curr.getLeft();
          } else { // Both branches are null, so remove the leaf
            if (result < 0) {
              parent.setRight(null);
            } else {
              parent.setLeft(null);
            }
            return curr.getValue();
          }
          newBranch.setParent(parent);
          if (result < 0) {
            parent.setRight(newBranch);
          } else {
            parent.setLeft(newBranch);
          }
        }
        return curr.getValue();
      } else if (result > 0) {
        curr = curr.getLeft();
      } else { // result < 0
        curr = curr.getRight();
      }
    }
    throw new IllegalArgumentException("Object does not exist");
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */
  @Override
  @SuppressWarnings("unchecked")
  public E[] preOrder() {
    return (E[]) preOrder(root).toArray();
  }

  private List<E> preOrder(Node<E> current) {
    List<E> order = new ArrayList<>();
    if (current == null) {
      return order;
    }
    order.add(current.getValue());
    order.addAll(preOrder(current.getLeft()));
    order.addAll(preOrder(current.getRight()));
    return order;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  @SuppressWarnings("unchecked")
  public E[] inOrder() {
    return (E[]) inOrder(root).toArray();
  }

  private List<E> inOrder(Node<E> current) {
    List<E> order = new ArrayList<>();
    if (current == null) {
      return order;
    }
    order.addAll(inOrder(current.getLeft()));
    order.add(current.getValue());
    order.addAll(inOrder(current.getRight()));
    return order;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects post-order
   */
  @Override
  @SuppressWarnings("unchecked")
  public E[] postOrder() {
    return (E[]) postOrder(root).toArray();
  }

  private List<E> postOrder(Node<E> current) {
    List<E> order = new ArrayList<>();
    if (current == null) {
      return order;
    }
    order.addAll(postOrder(current.getLeft()));
    order.addAll(postOrder(current.getRight()));
    order.add(current.getValue());
    return order;
  }

  /**
   * traverse through the tree and find out the tree height
   *
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    if (root == null) {
      throw new NullPointerException();
    }
    return findHeight(0, root);
  }

  private int findHeight(int currentHeight, Node<E> currentNode) {
    if (currentNode == null) {
      return currentHeight;
    } else {
      return max(findHeight(currentHeight + 1, currentNode.getLeft()),
          findHeight(currentHeight + 1, currentNode.getRight()));
    }
  }
}
