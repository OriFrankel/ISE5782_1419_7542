package geometries;

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
	Ray axisRay;
	double radius;

	/**
	 * constructor
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
		return null;
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
}