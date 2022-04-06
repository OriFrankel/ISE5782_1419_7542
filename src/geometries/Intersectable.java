/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * abstract class for something that can intersect with a ray
 * 
 * @author ori frankel and yair sprecher
 *
 */
public abstract class Intersectable {
	/**
	 * pair of a geometry and point on it
	 * 
	 * @author ori frankel and yair sprecher
	 *
	 */
	public class GeoPoint {
		/**
		 * the geometry
		 */
		public Geometry geometry;
		/**
		 * the point
		 */
		public Point point;

		/**
		 * constructor for geopoint, gets geometry and point
		 * 
		 * @param geometry the geometry
		 * @param point    the point
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.point = point;
			this.geometry = geometry;
		}

		@Override
		public boolean equals(Object other) {
			if (other == this)
				return true;
			if (other == null)
				return false;
			if (!(other instanceof GeoPoint))
				return false;
			GeoPoint geoPoint = (GeoPoint) other;
			return point.equals(geoPoint.point) && geometry.equals(geoPoint.geometry);
		}
		@Override
		public String toString() {
			return point.toString()+" in "+geometry.toString();
		}
	}

	/**
	 * returns the list of intersection points of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	public abstract List<Point> findIntersections(Ray ray);
	/**
	 * returns the list of intersection geopoints of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray){
		return findGeoIntersectionsHelper(ray);
	}
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
	

}
