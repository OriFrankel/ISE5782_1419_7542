package primitives;

import java.util.List;

/**
 * ray in the space
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
public class Ray {
	private final Point p0;
	private final Vector dir;

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
		return p0.toString() + dir.toString();
	}

	/**
	 * return point by the length of the ray till the point
	 * 
	 * @param d length till the point
	 * @return the point
	 */
	public Point getPoint(double d) {
		return Util.isZero(d) ? p0 : p0.add(dir.scale(d));
	}
	/**
	 * find the closest point to the start of the ray
	 * @param list list of points
	 * @return the closest point
	 */
	public Point findClosestPoint(List<Point> list) {
		if(list==null)return null;
		Point point=null;
		double dist2=Double.POSITIVE_INFINITY; 
		for(Point p2:list) {
			double d=p2.distanceSquared(p0);
			if(d<dist2) {
				point=p2;
				dist2=d;
			}
		}
		return point;
	}
}