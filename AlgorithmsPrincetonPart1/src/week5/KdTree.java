package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private static final int VERTICAL = 1;
	private static final int HORIZONTAL = 0;

	private Node root;

	private int size;

	private class Node {

		private Point2D point;
		private Node left;
		private Node right;
		private int division;

		public Node(Point2D point) {
			this.point = point;
			left = null;
			right = null;
		}
	}

	public KdTree() {
		// construct an empty set of points

		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		// is the set empty?

		return root == null;
	}

	public int size() {
		// number of points in the set

		return size;
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)

		if (p == null) {
			throw new NullPointerException();
		}

		if (!contains(p)) {

			Node node = new Node(p);

			root = insertNode(node, root);
			size++;
		}

	}

	private Node insertNode(Node node, Node parentNode) {

		if (parentNode == null) {
			node.division = VERTICAL;
			return node;
		}

		if (isLessThanPoint(node.point, parentNode.point, parentNode.division)) {
			if (parentNode.left == null) {
				node.division = getDivisionForNode(parentNode);
				parentNode.left = node;
			} else {
				parentNode.left = insertNode(node, parentNode.left);
			}
		} else {
			if (parentNode.right == null) {
				node.division = getDivisionForNode(parentNode);
				parentNode.right = node;
			} else {
				parentNode.right = insertNode(node, parentNode.right);
			}
		}

		return parentNode;
	}

	private int getDivisionForNode(Node node) {
		if (node.division == VERTICAL) {
			return HORIZONTAL;
		}
		return VERTICAL;
	}

	private boolean isLessThanPoint(Point2D nodePoint, Point2D pNodePoint, int division) {

		if (division == VERTICAL) {
			return nodePoint.x() < pNodePoint.x();
		}
		return nodePoint.y() < pNodePoint.y();
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?

		if (p == null) {
			throw new NullPointerException();
		}

		Node cur = root;

		while (true) {
			if (cur == null) {
				return false;
			}
			if (cur.point.equals(p)) {
				return true;
			}
			if (isLessThanPoint(p, cur.point, cur.division)) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
	}

	public void draw() {
		// draw all points to standard draw

		drawNode(root, 0, 1, 0, 1);
	}

	private void drawNode(Node node, double minX, double maxX, double minY, double maxY) {

		if (node == null) {
			return;
		}

		drawPoint(node.point);
		drawDivisionLine(node, minX, maxX, minY, maxY);

		if (node.division == VERTICAL) {
			drawNode(node.left, minX, node.point.x(), minY, maxY);
			drawNode(node.right, node.point.x(), maxX, minY, maxY);
		} else {
			drawNode(node.left, minX, maxX, minY, node.point.y());
			drawNode(node.right, minX, maxX, node.point.y(), maxY);
		}
	}

	private void drawPoint(Point2D point) {
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.BLACK);

		point.draw();
	}

	private void drawDivisionLine(Node node, double minX, double maxX, double minY, double maxY) {

		StdDraw.setPenRadius();
		if (node.division == VERTICAL) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(node.point.x(), minY, node.point.x(), maxY);
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(minX, node.point.y(), maxX, node.point.y());
		}

	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)

		if (rect == null) {
			throw new NullPointerException("Can't use empty rectangle");
		}

		Queue<Point2D> range = new Queue<>();

		nodeInRect(root, rect, range);

		return range;
	}

	private void nodeInRect(Node node, RectHV rect, Queue<Point2D> range) {
		if (node == null) {
			return;
		}

		if (rect.contains(node.point)) {
			range.enqueue(node.point);
		}

		if (node.division == VERTICAL) {
			if (node.point.x() > rect.xmin()) {
				nodeInRect(node.left, rect, range);
			}

			if (node.point.x() <= rect.xmax()) {
				nodeInRect(node.right, rect, range);
			}
		} else {
			if (node.point.y() > rect.ymin()) {
				nodeInRect(node.left, rect, range);
			}

			if (node.point.y() <= rect.ymax()) {
				nodeInRect(node.right, rect, range);
			}
		}
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty

		if (p == null) {
			throw new NullPointerException("Can't check empty point");
		}

		if (isEmpty()) {
			return null;
		}

		return nearestNode(p, root, root.point);
	}

	private Point2D nearestNode(Point2D point, Node node, Point2D nearest) {
		if (node == null) {
			return nearest;
		}

		if (node.point.distanceTo(point) < nearest.distanceTo(point)) {
			nearest = node.point;
		}

		Point2D nearestPossibleFromAnotherNode;
		Node morePrioritizedNode;
		Node lessPrioritizedNode;

		if (node.division == VERTICAL) {
			nearestPossibleFromAnotherNode = new Point2D(node.point.x(), point.y());

			if (point.x() < node.point.x()) {
				morePrioritizedNode = node.left;
				lessPrioritizedNode = node.right;
			} else {
				morePrioritizedNode = node.right;
				lessPrioritizedNode = node.left;
			}
		} else {
			nearestPossibleFromAnotherNode = new Point2D(point.x(), node.point.y());

			if (point.y() < node.point.y()) {
				morePrioritizedNode = node.left;
				lessPrioritizedNode = node.right;
			} else {
				morePrioritizedNode = node.right;
				lessPrioritizedNode = node.left;
			}
		}

		nearest = nearestNode(point, morePrioritizedNode, nearest);

		if (nearestPossibleFromAnotherNode.distanceTo(point) < nearest.distanceTo(point)) {
			nearest = nearestNode(point, lessPrioritizedNode, nearest);
		}

		return nearest;
	}

	public static void main(String[] args) {
		// unit testing of the methods (optional)
	}
}
