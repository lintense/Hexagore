package sn.thecells.support;

public class Point2D {
	public final int x;
	public final int y;
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point2D(float x, float y) {
		this.x = Math.round(x);
		this.y = Math.round(y);
	}
	public Point2D minus(Point2D p) {
		return new Point2D(x-p.x, y-p.y);
	}
	public Point2D dividedBy(float z) {
		return new Point2D(x/z, y/z);
	}
	public Point2D times(float z) {
		return new Point2D(x*z, y*z);
	}
	public Point2D squared() {
		return new Point2D(x*x, y*y);
	}
	
	
}
