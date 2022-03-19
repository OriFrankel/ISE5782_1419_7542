package geometries;

import primitives.Vector;
import primitives.Point;

/**
 * interface for geometrical shape
 * 
 * @author ori frankel and yair sprecher
 *
 */
public interface Geometry extends Intersectable {
	/**
	 * normal on a point in the geometry
	 * @param p the point
	 * @return the normal
	 */
	public Vector getNormal(Point p);
}