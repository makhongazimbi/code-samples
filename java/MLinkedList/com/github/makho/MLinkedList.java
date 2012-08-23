package com.github.makho;

/**
 * My naive implementation of singly linked list with basic
 * functionality.
 *
 * @author Makho Ngazimbi <makho.ngazimbi@gmail.com>
 *
 */
public class MLinkedList<T extends Comparable<? super T>> {
  protected Node<T> head = null;
  protected int size = 0;

  /**
   * Reverse the list.
   */
  public void reverse () {
    if (head == null) {
      return;
    }
    Node<T> prev = head;
    Node<T> next = head.next;
    prev.next = null;
    while (next != null) {
      head = next;
      next = head.next;
      head.next = prev;
      prev = head;
    }
  }

  /**
   * Add a value to the tail end of the list.
   */
  public void add (T value) {
    if (head == null) {
      head = new Node<T>(value);
    } else {
      Node<T> runner = head;
      while (runner.next != null) {
        runner = runner.next;
      }
      runner.next = new Node<T>(value);
    }
    size++;
  }

  /**
   * Remove a specified node.
   */
  protected void remove (Node<T> node) {
    Node<T> runner = head;
    if (runner == null) {
      return;
    }
    while (runner != null && runner.next != node) {
      runner = runner.next;
    }
    if (node.next != null) {
      runner.next = runner.next.next;
    } else {
      // removing node at end of list
      runner.next = null;
    }
    size--;
  }

  /**
   * Remove a node at a specified position.
   */
  public void removeAt (int index) {
    Node<T> node = this.nodeAt(index);
    this.remove(node);
  }

  public int size () {
    return size;
  }

  public void sort () {
    // 1. find min value in list
    // 2. Swap it with value in first position
    // 3. Repeat for remainder starting at position 2 and advancing
    Node<T> runner = head;
    while (runner != null) {
      Node<T> remainder = runner;
      Node<T> minimum = remainder;
      while (remainder != null) {
        if (remainder.data.compareTo(minimum.data) < 0) {
          minimum = remainder;
        }
        remainder = remainder.next;
      }
      // swap remainder with runner
      this.swap(minimum, runner);
      runner = runner.next;
    }
  }

  public void sortDescending () {
    this.sort();
    this.reverse();
  }

  protected void swap (Node<T> a, Node<T> b) {
    if (a == null || b == null) {
      return;
    }
    T tmp = a.data;
    a.data = b.data;
    b.data = tmp;
  }

  public String toString () {
    String output = "";
    Node<T> runner = head;
    while (runner != null) {
      output += runner.data + "\n";
      runner = runner.next;
    }
    return output;
  }

  public Node<T> nodeAt (int index) {
    Node<T> runner = head;
    for (int i = 0; i < index; i++) {
      if (runner.next == null) {
        break;
      }
      runner = runner.next;
    }
    return runner;
  }

  class Node<T extends Comparable<? super T>> {
    public T data;
    public Node<T> next = null;

    public Node (T d) {
      data = d;
    }

    public String toString () {
      return data.toString();
    }
  }

  public static void main (String [] args) {
    MLinkedList<String> list = new MLinkedList<String>();
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
    list.add("f");
    System.out.println("A list of strings:\n" + list);
    list.reverse();
    System.out.println("Reversing the list of strings:\n" + list);
    list.reverse();
    System.out.println("Reversing the list of string again:\n" + list);

    MLinkedList<Integer> integers = new MLinkedList<Integer>();
    integers.add(1);
    integers.add(2);
    integers.add(3);
    integers.add(4);
    integers.add(5);
    integers.add(-1);
    integers.add(-90);
    integers.add(45);
    integers.add(0);

    System.out.println("A list of integers:\n" + integers);
    integers.reverse();
    System.out.println("Reversing the list of integers:\n" + integers);
    System.out.println("Integer at position 0: " + integers.nodeAt(0));
    System.out.println("Integer at position 1: " + integers.nodeAt(1));
    System.out.println("Integer at position 2: " + integers.nodeAt(2));
    System.out.println("Integer at position 3: " + integers.nodeAt(3));
    System.out.println("Integer at position 4: " + integers.nodeAt(4));
    System.out.println("Integer at position 5: " + integers.nodeAt(5));

    integers.removeAt(3);
    System.out.println("Integer at position 3 removed:\n" + integers);
    integers.sort();
    System.out.println("Integers sorted ascending:\n" + integers);

    list.sortDescending();
    System.out.println("Strings list sorted descending:\n" + list);
  }

}
