package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * plane in the space
 * @author ori frankel and yair sprecher
 */
public class Plane implements Geometry {
	Point p0;
	Vector normal;
	/**
	 * constructor for Plane, gets point and normal
	 * @param p0 point on the plane
	 * @param normal normal to the plane
	 */
	public Plane(Point p0,Vector normal) {
		this.p0=p0;
		this.normal=normal.normalize();
	}
	/**
	 * constructor for Plane, gets three points
	 * @param p1 point on the plane
	 * @param p2 point on the plane
	 * @param p3 point on the plane
	 */
	public Plane(Point p1,Point p2,Point p3) {
		this(p1,p2.subtract(p1).crossProduct(p3.subtract(p1)));
	}
	/**
	 * getter for normal
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}
	@Override
	public Vector getNormal(Point p) {
		return getNormal();
	}
	/**
	 * getter for p0
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}

}
