package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;
import geometries.*;

/**
 * ray in the space
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
public class Ray {
	private final Point p0;
	private final Vector dir;
	private static final double DELTA = 0.01;

	/**
	 * Constructor for Ray, gets the approximated start point, direction and
	 * direction to move the point
	 * 
	 * @param point  approximated start point
	 * @param newDir direction
	 * @param n      direction to move the point
	 */
	public Ray(Point point, Vector newDir, Vector n) {
		this(point.add(n.scale(n.dotProduct(newDir) > 0 ? DELTA : -DELTA)), newDir);
	}

	/**
	 * constructor, for Ray, gets start point and direction
	 * 
	 * @param p the start point
	 * @param v the direction
	 */
	public Ray(Point p, Vector v) {
		p0 = p;
		dir = v.normalize();
	}

	/**
	 * getter for Dir
	 * 
	 * @return Dir
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * getter for p0
	 * 
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Ray))
			return false;
		Ray ray = (Ray) other;
		return ray.p0.equals(p0) && ray.dir.equals(dir);
	}

	@Override
	public String toString() {
		return "" + p0 + dir;
	}

	/**
	 * return point by the length of the ray till the point
	 * 
	 * @param d length till the point
	 * @return the point
	 */
	public Point getPoint(double d) {
		try {
			return Util.isZero(d) ? p0 : p0.add(dir.scale(d));
		} catch (IllegalArgumentException e) {
			return p0;
		}
	}

	/**
	 * find the closest point to the start of the ray
	 * 
	 * @param list list of points
	 * @return the closest point
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new Geometries().new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * find the closest geopoint to the start of the ray
	 * 
	 * @param list list of geopoints
	 * @return the closest geopoint
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> list) {
		if (list == null)
			return null;
		GeoPoint point = null;
		double dist2 = Double.POSITIVE_INFINITY;
		for (GeoPoint p2 : list) {
			double d = p2.point.distanceSquared(p0);
			if (d < dist2) {
				point = p2;
				dist2 = d;
			}
		}
		return point;
	}
}