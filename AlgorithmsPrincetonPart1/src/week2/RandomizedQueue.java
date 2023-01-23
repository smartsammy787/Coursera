package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] bag;
	private int n;

	// construct an empty randomized queue
	public RandomizedQueue() {
		bag = (Item[]) new Object[1];
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return n;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (n == bag.length) {
			resize(2 * bag.length);
		}
		bag[n] = item;
		n++;
	}

	private void resize(int capacity) {
		if (capacity < n) {
			throw new UnsupportedOperationException();
		}

		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < bag.length; i++) {
			copy[i] = bag[i];
		}
		bag = copy;
	}

	// remove and return a random item
	public Item dequeue() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int randIdx = StdRandom.uniform(n);
		Item elm = bag[randIdx];
		if (randIdx != n - 1) {
			bag[randIdx] = bag[n - 1];
		}

		bag[n - 1] = null;
		n--;
		if (n > 0 && n < bag.length / 4) {
			resize(bag.length / 2);
		}
		return elm;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int ranIdx = StdRandom.uniform(n);
		return bag[ranIdx];

	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator<Item>();
	}

	private class RandomizedQueueIterator<Item> implements Iterator<Item> {

		private Item[] copiedBag;
		private int idx;

		public RandomizedQueueIterator() {
			copiedBag = (Item[]) new Object[n];
			for (int i = 0; i < n; i++) {
				copiedBag[i] = (Item) bag[i];
			}
			StdRandom.shuffle(copiedBag);
		}

		@Override
		public boolean hasNext() {
			return idx != copiedBag.length;

		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			idx++;

			return copiedBag[idx - 1];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private String RandomizedQueueToString() {
		StringBuilder s = new StringBuilder();
		for (Item elm : this)
			s.append(elm + " ");
		return s.toString();
	}

	// unit testing (required)
	public static void main(String[] args) { // unit testing (optional)
		RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
		queue.enqueue(1);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(2);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(3);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(4);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(5);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(6);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(7);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(8);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(9);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		queue.enqueue(10);
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.dequeue());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.dequeue());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.dequeue());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.dequeue());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.dequeue());
		StdOut.println(queue.RandomizedQueueToString());
		StdOut.println(queue.RandomizedQueueToString());
	}

}
