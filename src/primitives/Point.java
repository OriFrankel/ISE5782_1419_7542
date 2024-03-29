/**
 * 
 */
package primitives;

/**
 * point in the space
 * 
 * @author Ori Frankel, Yair Sprecher
 * 
 */
public class Point {
	/**
	 * the coordinates
	 */
	final protected Double3 xyz;

	/**
	 * Center of coordinates' point
	 */
	public static final Point ZERO = new Point(Double3.ZERO);

	/**
	 * initalize using double3
	 * 
	 * @param xyz the double3
	 */
	protected Point(Double3 xyz) {
		this.xyz = xyz;
	}

	/**
	 * Constructor to initialize Point based object with its coordinates
	 * 
	 * @param d1 first coordinate
	 * @param d2 second coordinate
	 * @param d3 third coordinate
	 */
	public Point(double d1, double d2, double d3) {
		xyz = new Double3(d1, d2, d3);
	}

	/**
	 * getter for the x coordinate
	 * 
	 * @return x coordinate
	 */
	public double getX() {
		return xyz.d1;
	}

	/**
	 * getter for the y coordinate
	 * 
	 * @return y coordinate
	 */
	public double getY() {
		return xyz.d2;
	}

	/**
	 * getter for the z coordinate
	 * 
	 * @return z coordinate
	 */
	public double getZ() {
		return xyz.d3;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this)
			return true;
		if (arg0 == null)
			return false;
		if (!(arg0 instanceof Point))
			return false;
		Point otherPoint = (Point) arg0;
		return xyz.equals(otherPoint.xyz);
	}

	@Override
	public String toString() {
		return xyz.toString();
	}

	/**
	 * add vector to point
	 * 
	 * @param vector the vector
	 * @return the sum
	 */
	public Point add(Vector vector) {
		return new Point(this.xyz.add(vector.xyz));
	}

	/**
	 * subtract point from point
	 * 
	 * @param point the point
	 * @return the resulting vector
	 */
	public Vector subtract(Point point) {
		return new Vector(xyz.subtract(point.xyz));
	}

	/**
	 * the distance from another point,squared
	 * 
	 * @param other another point
	 * @return the distance squared
	 */
	public double distanceSquared(Point other) {
		return (xyz.d1 - other.xyz.d1) * (xyz.d1 - other.xyz.d1) + (xyz.d2 - other.xyz.d2) * (xyz.d2 - other.xyz.d2)
				+ (xyz.d3 - other.xyz.d3) * (xyz.d3 - other.xyz.d3);
	}

	/**
	 * the distance from another point
	 * 
	 * @param other another point
	 * @return the distance
	 */
	public double distance(Point other) {
		return Math.sqrt(distanceSquared(other));
	}
}
