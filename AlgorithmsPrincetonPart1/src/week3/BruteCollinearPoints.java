package week3;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	private final LineSegment[] segments;

	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points

		if (points == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException();
			}
		}

		int length = points.length;
		Point[] clonePoints = points.clone();
		LineSegment[] tempSegments = new LineSegment[length * length];
		int count = 0;

		Point pa, pb, pc, pd;

		Arrays.sort(clonePoints);

		for (int i = 0; i < length - 1; i++) {
			if (clonePoints[i].compareTo(clonePoints[i + 1]) == 0) {
				throw new IllegalArgumentException();
			}
		}

		for (int a = 0; a < length; a++) {
			for (int b = a + 1; b < length; b++) {
				for (int c = b + 1; c < length; c++) {
					for (int d = c + 1; d < length; d++) {
						pa = clonePoints[a];
						pb = clonePoints[b];
						pc = clonePoints[c];
						pd = clonePoints[d];
						if (pa.slopeTo(pb) == pa.slopeTo(pc) && pa.slopeTo(pc) == pa.slopeTo(pd)) {
							tempSegments[count++] = new LineSegment(pa, pd);
						}
					}
				}
			}
		}

		segments = new LineSegment[count];
		for (int i = 0; i < count; i++) {
			segments[i] = tempSegments[i];
		}

	}

	public int numberOfSegments() {
		// the number of line segments
		return segments.length;
	}

	public LineSegment[] segments() {

		// the line segments
		int length=numberOfSegments();
		LineSegment[] copy=new LineSegment[length];
		for(int i=0;i<length;i++) {
			copy[i]=segments[i];
		}
		return copy;
	}
	
	 public static void main(String[] args) {

	        // read the n points from a file
	        In in = new In(args[0]);
	        int n = in.readInt();
	        Point[] points = new Point[n];
	        for (int i = 0; i < n; i++) {
	            int x = in.readInt();
	            int y = in.readInt();
	            points[i] = new Point(x, y);
	        }

	        // draw the points
	        StdDraw.enableDoubleBuffering();
	        StdDraw.setXscale(0, 32768);
	        StdDraw.setYscale(0, 32768);
	        for (Point p : points) {
	            p.draw();
	        }
	        StdDraw.show();

	        // print and draw the line segments
	        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	        for (LineSegment segment : collinear.segments()) {
	            StdOut.println(segment);
	            segment.draw();
	        }
	        StdDraw.show();
	    }
	 
	 
}
