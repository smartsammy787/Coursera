package week4;
import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

public class Board {

	// create a board from an n-by-n array of tiles,
	// where tiles[row][col] = tile at (row, col)

	private final int n;
	private int grid[][];

	public Board(int[][] tiles) {
		n = tiles.length;
		grid = new int[n][n];

		for (int i = 0; i < n; i++) {
			grid[i] = tiles[i].clone();
		}
	}

	// string representation of this board
	public String toString() {
		StringBuilder s = new StringBuilder();
		int n = grid.length;
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", grid[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	// board dimension n
	public int dimension() {
		return n;
	}

	// number of tiles out of place
	public int hamming() {

		int res=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]!=0 && grid[i][j]!=i*n+j+1) {
					res++;
				}
			}
		}
		
		return res;
	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
        int re = 0;
        int yVal, xVal;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    yVal = i - (grid[i][j]-1) / n;
                    xVal = j - (grid[i][j]-1) % n;
                    re += (yVal < 0) ? -yVal : yVal;
                    re += (xVal < 0) ? -xVal : xVal;
                }
            }
        }
        return re;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return (hamming() == 0);
	}

	// does this board equal y?
	public boolean equals(Object y) {

		if(this==y) {
			return true;
		}
		if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (grid.length != that.grid.length ||
                grid[0].length != that.grid[0].length) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != that.grid[i][j]) return false;
            }
        }
        return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		int[][] temp = new int[n][n];

		for (int i = 0; i < n; i++) {
			temp[i] = grid[i].clone();
		}

		Stack<Board> st = new Stack<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == 0) {

					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							if ((dx != 0 || dy != 0) && (dx == 0 || dy == 0) && i + dx >= 0 && i + dx < grid.length
									&& j + dy >= 0 && j + dy < grid.length) {
								temp[i][j] = temp[i + dx][j + dy];
								temp[i + dx][j + dy] = 0;

								st.push(new Board(temp));

								temp[i + dx][j + dy] = temp[i][j];
								temp[i][j] = 0;
							}
						}
					}
					return st;
				}
			}
		}
		return st;
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		int [][] newGrid = new int [n][n];
        for (int i = 0; i < n; i++) {
            newGrid[i] = grid[i].clone();
        }

        int tmp, rowNum = 0;
        // Just switch from the upper four squares
        if (newGrid[0][0] == 0 || newGrid[0][1] == 0) {
            rowNum = 1;
        }
        tmp = newGrid[rowNum][0];
        newGrid[rowNum][0] = newGrid[rowNum][1];
        newGrid[rowNum][1] = tmp;

        return new Board(newGrid);
	}

	// unit testing (not graded)

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] arr = {{8, 2, 3}, {4, 5, 6}, {7, 1, 0}};
        Board b = new Board(arr);
        StdOut.println("=== SOURCE ===");
        StdOut.println(b.toString());

        Iterable<Board> neighbours = b.neighbors();
        for (Board n : neighbours) {
            StdOut.println(n.toString());
        }
	}

}
