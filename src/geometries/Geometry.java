package geometries;

import primitives.Vector;
import primitives.Point;

/**
 * interface for geometrical shape
 * 
 * @author ori frankel and yair sprecher
 *
 */
public interface Geometry {
	public Vector getNormal(Point p);
}
