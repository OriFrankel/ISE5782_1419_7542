package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * sphere in the space
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Sphere implements Geometry {
	private final Point center;
	private final double radius;

	/**
	 * constructor for sphere gets center and radius
	 * 
	 * @param center the center
	 * @param radius the radius
	 */
	public Sphere(double radius,Point center) {
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
	@Override
	public List<Point> findIntersections(Ray ray){
		if (ray.getP0().equals(center))
			return List.of(ray.getPoint(radius));
		Vector u = center.subtract(ray.getP0());
		double tm = ray.getDir().dotProduct(u);
		double d = u.lengthSquared() - tm * tm;
		double temp = radius * radius - d;
		if (Util.alignZero(temp) <= 0)
			return null;
		double th = Math.sqrt(temp);
		if (Util.alignZero(tm - th) > 0)
			return List.of(ray.getPoint(tm - th), ray.getPoint(tm + th));
		if (Util.alignZero(tm + th) > 0)
			return List.of(ray.getPoint(tm + th));
		return null;
	}
}