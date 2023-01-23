package week1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double mean;

	private double stdev;

	private double loConfidenceValue;

	private double hiConfidenceValue;

	private final double CON_VAL = 1.96;

	public PercolationStats(int n, int trials) {

		if (n < 1) {
			throw new IllegalArgumentException("Grid must have at least one row and column");
		}

		if (trials < 1) {
			throw new IllegalArgumentException("You must run percolation at least once");
		}

		double[] result = new double[trials];
		int row;
		int col;

		for (int i = 0; i < trials; i++) {

			Percolation p = new Percolation(n);

			while (!p.percolates()) {
				do {
					row = (int) (StdRandom.uniform() * n) + 1;
					col = (int) (StdRandom.uniform() * n) + 1;
				} while (p.isOpen(row, col));

				p.open(row, col);

			}

			result[i] = (double) (p.numberOfOpenSites()) / (n * n);
		}

		mean = StdStats.mean(result);
		stdev = StdStats.stddev(result);

		loConfidenceValue = mean - (CON_VAL * stdev) / Math.sqrt(trials);
		hiConfidenceValue = mean + (CON_VAL * stdev) / Math.sqrt(trials);
	}

	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return stdev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return loConfidenceValue;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return hiConfidenceValue;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int gridLength = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);

		PercolationStats stats = new PercolationStats(gridLength, trials);

		StdOut.println("mean = " + stats.mean());
		StdOut.println("stddev = " + stats.stddev());
		StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	}

}
