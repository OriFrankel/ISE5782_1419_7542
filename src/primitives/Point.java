/**
 * 
 */
package primitives;
/**
 * @author Ori Frankel, Yair Sprecher
 * point in the space
 */
public class Point {
	/**
	 * the coordinates
	 */
	final Double3 xyz;
	Point(Double3 xyz){
		this.xyz=xyz;
	}
	/**
	 * Constructor to initialize Point based object with its coordinates
	 * 
	 * @param d1 first coordinate
	 * @param d2 second coordinate
	 * @param d3 third coordinate
	 */
	public Point(double d1,double d2,double d3) {
		xyz=new Double3(d1,d2,d3);
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0==this) return true;
		if(arg0==null) return false;
		if(!(arg0 instanceof Point))return false;
		Point otherPoint=(Point)arg0;
		return xyz.equals(otherPoint.xyz);
	}
	@Override
	public String toString() {
		return xyz.toString();
	}
	/**
	 * add vector to point
	 * @param vector the vector
	 * @return the sum
	 */
	public Point add(Vector vector) {
		return new Point(this.xyz.add(vector.xyz));
	}
	/**
	 * subtract point from point
	 * @param point the point
	 * @return the resulting vector
	 */
	public Vector subtract(Point point) {
		return new Vector(xyz.subtract(point.xyz));
	}
	public double distanceSquared(Point other) {
		return subtract(other).lengthSquared();
	}
	public double distance(Point other) {
		return Math.sqrt(distanceSquared(other));
	}
}
