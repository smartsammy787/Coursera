package week4;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private class Node implements Comparable<Node> {
		private final Board board;
		private final int moves;
		private final int priority; // Manhattan
		private final Node predecessor;

		public Node(Board board, int moves, Node predecessor) {
			this.board = board;
			this.moves = moves;
			this.predecessor = predecessor;
			this.priority = board.manhattan() + this.moves;
		}

		public int compareTo(Node that) {
			if (this.priority < that.priority)
				return -1;
			else if (this.priority > that.priority)
				return 1;
			else
				return 0;
		}

		public Iterable<Board> neighbours() {
			return board.neighbors();
		}

		public boolean isGoal() {
			return moves == priority;
		}

	}

	private boolean solvable;

	private Node node;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null)
			throw new IllegalArgumentException();

		MinPQ<Node> pq = new MinPQ<>();
		pq.insert(new Node(initial, 0, null));
		pq.insert(new Node(initial.twin(), 0, null));

		while (!pq.isEmpty()) {
			Node curr = pq.delMin();
			if (curr.isGoal()) {
				Node tmp = curr;
				while (tmp.predecessor != null) {
					tmp = tmp.predecessor;
				}
				solvable = tmp.board.equals(initial);
				node = curr;
				break;

			}

			Iterable<Board> neighbours = curr.neighbours();
			Node pred = curr.predecessor;
			Board predBoard = pred == null ? null : pred.board;

			for (Board b : neighbours) {
				if (!b.equals(predBoard)) {
					pq.insert(new Node(b, curr.moves + 1, curr));
				}
			}

		}

	}

	// is the initial board solvable? (see below)
	public boolean isSolvable() {
		return solvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (solvable)
			return node.moves;
		else
			return -1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {

		if (!solvable) {
			return null;
		}

		Stack<Board> s = new Stack();
		Node tmp = node;

		while (tmp != null) {
			s.push(tmp.board);
			tmp = tmp.predecessor;
		}
		return s;
	}

	// test client (see below)
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] tiles = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				tiles[i][j] = in.readInt();
		Board initial = new Board(tiles);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
