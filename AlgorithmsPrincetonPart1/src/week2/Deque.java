package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;
	}

	private Node<Item> first, last;
	private int n;

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		n = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// return the number of items on the deque
	public int size() {
		return n;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		Node newNode = new Node();
		newNode.item = item;
		if (n == 0) {
			last = newNode;
		} else if (n == 1) {
			newNode.next = last;
			last.prev = newNode;
		} else {
			first.prev = newNode;
			newNode.next = first;
		}
		first = newNode; // old first pointing to new first
		n++;
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		Node<Item> newNode = new Node<>();
		newNode.item = item;

		if (n == 0) {
			first = newNode;
		} else if (n == 1) {
			newNode.prev = first;
			first.next = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
		}
		last = newNode;
		n++;
	}

	// remove and return the item from the front
	public Item removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) {
			last = null;
		} else {
			first.prev = null;
		}

		return item;

	}

	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item elm = last.item;

		if (n == 1) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}

		n--;
		return elm;

	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node<Item> curr = first;

		@Override
		public boolean hasNext() {

			return curr != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = curr.item;
			curr = curr.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private String DequeToString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	public static void main(String[] args) { // unit testing (optional)

		/*
		 * Deque<String> deque = new Deque<String>(); deque.addFirst("1");
		 * StdOut.println("addfirst to string: " + deque.DequeToString());
		 * deque.addFirst("2"); StdOut.println("addfirst to string: " +
		 * deque.DequeToString()); deque.addFirst("3");
		 * StdOut.println("addfirst to string: " + deque.DequeToString());
		 * deque.addFirst("4"); StdOut.println("addfirst to string: " +
		 * deque.DequeToString()); deque.addFirst("5");
		 * StdOut.println("addfirst to string: " + deque.DequeToString());
		 * deque.removeFirst();
		 * StdOut.println("removefirst to string: "+deque.DequeToString());
		 * deque.removeFirst();
		 * StdOut.println("removefirst to string: "+deque.DequeToString());
		 * deque.removeFirst();
		 * StdOut.println("removefirst to string: "+deque.DequeToString());
		 * deque.removeFirst();
		 * StdOut.println("removefirst to string: "+deque.DequeToString());
		 * deque.removeFirst();
		 * StdOut.println("removefirst to string: "+deque.DequeToString());
		 * deque.addLast("1");
		 * StdOut.println("addlast to string: "+deque.DequeToString());
		 * deque.addLast("2");
		 * StdOut.println("addlast to string: "+deque.DequeToString());
		 * deque.addLast("3");
		 * StdOut.println("addlast to string: "+deque.DequeToString());
		 * deque.addLast("4");
		 * StdOut.println("addlast to string: "+deque.DequeToString());
		 * deque.addLast("5");
		 * StdOut.println("addlast to string: "+deque.DequeToString());
		 * deque.removeLast(); StdOut.println("removelast to string: "+
		 * deque.DequeToString()); deque.removeLast();
		 * StdOut.println("removelast to string: "+ deque.DequeToString());
		 * deque.removeLast(); StdOut.println("removelast to string: "+
		 * deque.DequeToString()); deque.removeLast();
		 * StdOut.println("removelast to string: "+ deque.DequeToString());
		 * deque.removeLast(); StdOut.println("removelast to string: "+
		 * deque.DequeToString()); //deque.addLast(null); //deque.addFirst(null);
		 * ///deque.removeLast(); //deque.removeFirst();
		 */
		Deque<Integer> dq = new Deque<Integer>();
		StdOut.println(dq.isEmpty());
		dq.addLast(7);
		dq.addFirst(1);
		dq.addFirst(3);
		dq.addLast(8);
		for (int i : dq) {
			StdOut.println(i);
		}

		dq.removeFirst();
		dq.removeLast();
		dq.removeLast();
		StdOut.println();
		for (int i : dq) {
			StdOut.println(i);
		}
	}

}