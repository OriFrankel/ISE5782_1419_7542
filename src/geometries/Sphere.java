package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * sphere in the space
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Sphere extends Geometry {
	private final Point center;
	private final double radius;

	/**
	 * constructor for sphere gets center and radius
	 * 
	 * @param center the center
	 * @param radius the radius
	 */
	public Sphere(double radius, Point center) {
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
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		Point p0 = ray.getP0();
		if (p0.equals(center))
			return List.of(new GeoPoint(this, ray.getPoint(radius)));
		Vector u = center.subtract(p0);
		double tm = ray.getDir().dotProduct(u);
		double d2 = u.lengthSquared() - tm * tm;
		double temp = radius * radius - d2;
		if (Util.alignZero(temp) <= 0)
			return null;
		double th = Math.sqrt(temp);
		double t2 = alignZero(tm + th);
		if (t2 <= 0)
			return null;
		double t1 = alignZero(tm - th);
		return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2))) : List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
	}
}