package week5;

import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

	private final TreeSet<Point2D> set;

	public PointSET() {
		// construct an empty set of points
		set = new TreeSet<Point2D>();

	}

	public boolean isEmpty() {
		// is the set empty?
		return set.isEmpty();
	}

	public int size() {
		// number of points in the set
		return set.size();
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null) {
			throw new IllegalArgumentException();
		}
		set.add(p);
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		if(p==null) {
			throw new IllegalArgumentException();
		}
		return set.contains(p);
	}

	public void draw() {
		// draw all points to standard draw
		for(Point2D point:set) {
			point.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		
		if(rect==null) {
			throw new IllegalArgumentException();
		}
		
		Queue<Point2D> q=new Queue<Point2D>();
		
		for(Point2D point:set) {
			if(rect.contains(point)) {
				q.enqueue(point);
			}
		}
		
		return q;
		
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		
		if(p==null) {
			 throw new NullPointerException("Can't check empty point");
		}
		
		if (isEmpty()) {
            return null;
        }
		Point2D nearestPoint=null;
		
		for(Point2D point: set) {
			if(point==null || p.distanceTo(point)< p.distanceTo(nearestPoint)) {
				nearestPoint=point;
			}
		}
		
		return nearestPoint;
	}

	public static void main(String[] args) {
		// unit testing of the methods (optional)
		
	}
}
