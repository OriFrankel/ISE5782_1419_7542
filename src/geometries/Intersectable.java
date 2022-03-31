/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * interface for something that can intersect with a ray
 * 
 * @author ori frankel and yair sprecher
 *
 */
public interface Intersectable {
	/**
	 * returns the list of intersection points of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray);

}
