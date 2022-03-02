/**
 * 
 */
package primitives;

/**
 * vector in the space
 * @author ori frankel, yair sprecher
 *
 */
public class Vector extends Point {
	/**
	 * constructor
	 * @param d1 the first coordinate of endpoint
	 * @param d2 the second coordinate of endpoint
	 * @param d3 the third coordinate of endpoint
	 */
	public Vector(double d1,double d2,double d3) {
		super(d1, d2, d3);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Zero vector\n");
	}
	Vector(Double3 xyz){
		super(xyz);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Zero vector\n");
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0==this) return true;
		if(arg0==null) return false;
		if(!(arg0 instanceof Vector))return false;
		Vector otherVector=(Vector)arg0;
		return super.equals((Point)otherVector);
	}
	@Override
	public String toString() {
		return "->"+super.toString();
	}
	/**
	 * add another vector
	 * @param v the vector
	 * @return the sum
	 */
	public Vector add(Vector v) {
		return (Vector)super.add(v);
	}
	/**
	 * scale by a scalar
	 * @param d the scalar
	 * @return the scaled vector
	 */
	public Vector scale(double d) {
		return new Vector(xyz.scale(d));
	}
	/**
	 * dot product
	 * @param v another vector
	 * @return the product
	 */
	public double dotProduct(Vector v) {
		Double3 d3 = xyz.product(v.xyz);
		return d3.d1 + d3.d2 +d3.d3;
	}
	/**
	 * cross product of two vectors
	 * @param v another vector
	 * @return the product
	 */
	public Vector crossProduct(Vector v) {
		//using the formula for cross product
		return new Vector(xyz.d2 * v.xyz.d3 - v.xyz.d2 * xyz.d3, xyz.d3 * v.xyz.d1 - v.xyz.d3 * xyz.d1, xyz.d1 * v.xyz.d2 - v.xyz.d1 * xyz.d2);
	}
	/**
	 * the length of the vector, squared
	 * @return the squared length
	 */
	public double lengthSquared() {
		return dotProduct(this);
	}
	/**
	 * the length of the vector
	 * @return the  length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	/**
	 * normalize the vector to unit vector
	 * @return the normalized vector
	 */
	public Vector normalize() {
		return scale(1/length());
	}
	
}
