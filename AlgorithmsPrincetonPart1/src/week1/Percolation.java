package week1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	// gridLength
	private int n;

	private int opensites;

	private boolean[] sites;

	// Using quick union to find if sites are connected or not
	private WeightedQuickUnionUF uf;

	private int virtualTopIndex;

	private int virtualBotIndex;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {

		if (n < 1) {
			throw new IllegalArgumentException("Grid must have at least one row and column");
		}

		this.n = n;

		uf = new WeightedQuickUnionUF((n * n) + 2);

		virtualTopIndex = 0;

		virtualBotIndex = n * n + 1;
		opensites = 0;

		sites = new boolean[(n * n) + 2];
		sites[virtualTopIndex] = true;

		sites[virtualBotIndex] = false;

		for (int col = 1; col <= n; col++) {
			int toprow = 1;
			int index = getIndexByRowCol(toprow, col);
			uf.union(virtualTopIndex, index);

			int bottomRow = n;

			int botIndex = getIndexByRowCol(bottomRow, col);
			uf.union(virtualBotIndex, botIndex);
		}
	}

	private int getIndexByRowCol(int row, int col) {

		validateBounds(row, col);

		int index = ((row - 1) * n) + col;

		return index;
	}

	private void validateBounds(int row, int col) {
		if (row > n || row < 1) {
			throw new IndexOutOfBoundsException("Row index is out of bounds");
		}

		if (col > n || col < 1) {
			throw new IndexOutOfBoundsException("Column index is out of bounds");
		}
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {

		int siteIndex = getIndexByRowCol(row, col);

		if (sites[siteIndex]) {
			return;
		}
		sites[siteIndex] = true;
		opensites++;

		// connect all open neighbor sites to this site

		if (col > 1 && isOpen(row, col - 1)) { // left neighbor
			int leftSiteIndex = getIndexByRowCol(row, col - 1);

			uf.union(siteIndex, leftSiteIndex);
		}

		if (col < n && isOpen(row, col + 1)) {

			int rightSiteIndex = getIndexByRowCol(row, col + 1);

			uf.union(siteIndex, rightSiteIndex);
		}

		if (row > 1 && isOpen(row - 1, col)) {

			int topSiteIndex = getIndexByRowCol(row - 1, col);

			uf.union(siteIndex, topSiteIndex);
		}

		if (row < n && isOpen(row + 1, col)) {

			int botSiteIndex = getIndexByRowCol(row + 1, col);

			uf.union(siteIndex, botSiteIndex);
		}

	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {

		int siteIndex = getIndexByRowCol(row, col);

		return sites[siteIndex];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {

		int siteIndex = getIndexByRowCol(row, col);

		return (isOpen(row, col) && uf.find(virtualTopIndex) == uf.find(siteIndex));
	}

	// returns the number of open sites
	public int numberOfOpenSites() {

		return opensites;
	}

	// does the system percolate?

	public boolean percolates() {

		if (n == 1) {
			int siteIndex = getIndexByRowCol(1, 1);

			return sites[siteIndex];
		}

		return uf.find(virtualTopIndex) == uf.find(virtualBotIndex);

	}

	// test client (optional)
	public static void main(String[] args) {

		Percolation obj = new Percolation(2);

		StdOut.println("percolates = " + obj.percolates());

		StdOut.println("isOpen(1, 2) = " + obj.isOpen(1, 2));
		StdOut.println("isFull(1, 2) = " + obj.isFull(1, 2));
		StdOut.println("open(1, 2)");
		obj.open(1, 2);
		StdOut.println("isOpen(1, 2) = " + obj.isOpen(1, 2));
		StdOut.println("isFull(1, 2) = " + obj.isFull(1, 2));
		StdOut.println("numberOfOpenSites() = " + obj.numberOfOpenSites());
		StdOut.println("percolates() = " + obj.percolates());

		StdOut.println("isOpen(2, 1) = " + obj.isOpen(2, 1));
		StdOut.println("isFull(2, 1) = " + obj.isFull(2, 1));
		StdOut.println("open(2, 1)");
		obj.open(2, 1);
		StdOut.println("isOpen(2, 1) = " + obj.isOpen(2, 1));
		StdOut.println("isFull(2, 1) = " + obj.isFull(2, 1));
		StdOut.println("numberOfOpenSites() = " + obj.numberOfOpenSites());
		StdOut.println("percolates() = " + obj.percolates());

		StdOut.println("isOpen(1, 1) = " + obj.isOpen(1, 1));
		StdOut.println("isFull(1, 1) = " + obj.isFull(1, 1));
		StdOut.println("open(1, 1)");
		obj.open(1, 1);
		StdOut.println("isOpen(1, 1) = " + obj.isOpen(1, 1));
		StdOut.println("isFull(1, 1) = " + obj.isFull(1, 1));
		StdOut.println("numberOfOpenSites() = " + obj.numberOfOpenSites());
		StdOut.println("percolates() = " + obj.percolates());

	}
}
