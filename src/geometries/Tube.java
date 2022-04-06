package geometries;

import java.util.List;

import primitives.*;

/**
 * infinite tube
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Tube extends Geometry {
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
		Point o = axisRay.getPoint(axisRay.getDir().dotProduct(v));
		return p.subtract(o).normalize();
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
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		return null;
	}
}