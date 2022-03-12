package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * sphere in the space
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Sphere implements Geometry {
	Point center;
	double radius;

	/**
	 * constructor for sphere gets center and radius
	 * 
	 * @param center the center
	 * @param radius the radius
	 */
	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}

	/**
	 * getter for center
	 * 
	 * @return the center
	 */
	public Point getCenter() {
		return center;
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
		return "Sphere with center " + center.toString() + " and radius " + ((Double) radius).toString();
	}
}