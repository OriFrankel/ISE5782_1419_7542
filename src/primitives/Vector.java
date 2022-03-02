/**
 * 
 */
package primitives;

/**
 * @author ori frankel, yair sprecher
 *
 */
public class Vector extends Point {
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
	public boolean equals(Object arg0) {
		if(arg0==this) return true;
		if(arg0==null) return false;
		if(!(arg0 instanceof Vector))return false;
		Vector otherVector=(Vector)arg0;
		return super.equals((Point)otherVector);
	}
	public String toString() {
		return "->"+super.toString();
	}
	public Vector add(primitives.Vector v) {
		return (Vector)super.add(v);
	}
	public Vector scale(double d) {
		return new Vector(xyz.scale(d));
	}
	public double dotProduct(Vector v) {
		Double3 d3 = xyz.product(v.xyz);
		return d3.d1 + d3.d2 +d3.d3;
	}
	public Vector crossProduct(Vector v) {
		return new Vector(xyz.d2 * v.xyz.d3 - v.xyz.d2 * xyz.d3, xyz.d3 * v.xyz.d1 - v.xyz.d3 * xyz.d1, xyz.d1 * v.xyz.d2 - v.xyz.d1 * xyz.d2);
	}
	public double lengthSquared() {
		return dotProduct(this);
	}
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	public Vector normalize() {
		return scale(1/length());
	}
	
}
