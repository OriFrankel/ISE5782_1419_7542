package geometries;

import java.util.List;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * infinite tube
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Tube implements Geometry {
	protected final Ray axisRay;
	protected final double radius;

	/**
	 * constructor for Tube gets axis ray and radius
	 * 
	 * @param ray the axis
	 * @param d   the radius
	 */
	public Tube(Ray ray, double d) {
		axisRay = ray;
		radius = d;
	}

	@Override
	public Vector getNormal(Point p) {
		Vector v = p.subtract(axisRay.getP0());
		try {
			Vector vector=axisRay.getDir();
			return v.subtract(vector.scale(vector.dotProduct(v))).normalize();
		} catch(IllegalArgumentException e) {
			return v.normalize();
		}
	}

	/**
	 * getter for axisRay
	 * 
	 * @return the axis
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getter for radius
	 * 
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return axisRay.toString() + " and radius: " + ((Double) radius).toString();
	}
	@Override
	public List<Point> findIntersections(Ray ray){
		return null;
	}
}