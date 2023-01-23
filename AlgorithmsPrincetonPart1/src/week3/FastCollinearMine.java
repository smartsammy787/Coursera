package week3;
/*
 * import java.util.ArrayList; import java.util.Arrays; import
 * java.util.Collections; import java.util.HashMap;
 * 
 * import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdDraw;
 * import edu.princeton.cs.algs4.StdOut;
 * 
 * public class FastCollinearPoints {
 * 
 * private LineSegment[] lineSegments;
 * 
 * private ArrayList<LineSegment> lineSegmentList = new ArrayList<>();
 * 
 * private HashMap<Double, ArrayList<Point>> startingPointMap = new HashMap<>();
 * 
 * public FastCollinearPoints(Point[] points) {
 * 
 * // finds all line segments containing 4 or more points
 * 
 * if (isNull(points)) { throw new
 * NullPointerException("Points array contains null values"); }
 * 
 * Point[] pointCopy = Arrays.copyOf(points, points.length);
 * Arrays.sort(pointCopy);
 * 
 * if (isDuplicatedPoints(pointCopy)) { throw new
 * IllegalArgumentException("Points array can't have duplicate points"); }
 * 
 * for (Point startingPoint : points) { Arrays.sort(pointCopy,
 * startingPoint.slopeOrder());
 * 
 * ArrayList<Point> sameSlopePoints = new ArrayList<>(); double currentSlope =
 * 0; double prevSlope = Double.NEGATIVE_INFINITY;
 * 
 * for (int i = 1; i < pointCopy.length; i++) { currentSlope =
 * startingPoint.slopeTo(pointCopy[i]);
 * 
 * if (currentSlope == prevSlope) { sameSlopePoints.add(pointCopy[i]); } else {
 * addSegments(sameSlopePoints, startingPoint, prevSlope);
 * 
 * sameSlopePoints.clear();
 * 
 * sameSlopePoints.add(pointCopy[i]); }
 * 
 * prevSlope = currentSlope; } lineSegments = lineSegmentList.toArray(new
 * LineSegment[lineSegmentList.size()]); } }
 * 
 * private void addSegments(ArrayList<Point> sameSlopePoints, Point
 * startingPoint, double slopeKey) {
 * 
 * if (sameSlopePoints.size() < 3) { return; }
 * 
 * sameSlopePoints.add(startingPoint);
 * 
 * ArrayList<Point> startPoints = startingPointMap.get(slopeKey);
 * Collections.sort(sameSlopePoints);
 * 
 * Point startPoint = sameSlopePoints.get(0); Point endPoint =
 * sameSlopePoints.get(sameSlopePoints.size() - 1);
 * 
 * if (startPoints == null) {
 * 
 * startPoints = new ArrayList<>(); startPoints.add(startPoint);
 * startingPointMap.put(slopeKey, startPoints);
 * 
 * } else {
 * 
 * for (Point point : startPoints) { if (startingPoint.compareTo(point) == 0) {
 * return; } } startPoints.add(startPoint); } }
 * 
 * private boolean isNull(Point[] points) {
 * 
 * if (points == null) { return true; }
 * 
 * for (Point point : points) { if (point == null) { return true; } } return
 * false; }
 * 
 * private boolean isDuplicatedPoints(Point[] points) { for (int i = 0; i <
 * points.length-1; i++) { if (points[i].compareTo(points[i + 1]) == 0) { return
 * true; } } return false; }
 * 
 * public int numberOfSegments() { // the number of line segments return
 * lineSegments.length; }
 * 
 * public LineSegment[] segments() { // the line segments return
 * Arrays.copyOf(lineSegments, lineSegments.length); }
 * 
 * public static void main(String[] args) {
 * 
 * // read the n points from a file In in = new In(args[0]); int n =
 * in.readInt(); Point[] points = new Point[n]; for (int i = 0; i < n; i++) {
 * int x = in.readInt(); int y = in.readInt(); points[i] = new Point(x, y); }
 * 
 * // draw the points StdDraw.enableDoubleBuffering(); StdDraw.setXscale(0,
 * 32768); StdDraw.setYscale(0, 32768); for (Point p : points) { p.draw(); }
 * StdDraw.show();
 * 
 * // print and draw the line segments FastCollinearPoints collinear = new
 * FastCollinearPoints(points); for (LineSegment segment : collinear.segments())
 * { StdOut.println(segment); segment.draw(); } StdDraw.show(); } }
 */
