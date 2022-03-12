package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * finite cylinder
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Cylinder extends Tube {
	double height;

	/**
	 * constructor for Cylinder gets axis,radius and height
	 * @param ray    the axis
	 * @param radius the radius
	 * @param height the height
	 */
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}
	/**
	 * getter for height
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	@Override
	public String toString() {
		return super.toString() + " height: " + ((Double) height).toString();
	}
	@Override
	public Vector getNormal(Point p) {
		if (p.equals(axisRay.getP0())) {
			return axisRay.getDir().scale(-1);
		}
		Vector v = p.subtract(axisRay.getP0());
		double d = v.dotProduct(axisRay.getDir());
		if (primitives.Util.isZero(d)) {
			return axisRay.getDir().scale(-1);
		}
		if (primitives.Util.isZero(d - height)) {
			return axisRay.getDir();
		}
		return super.getNormal(p);
	}
}
