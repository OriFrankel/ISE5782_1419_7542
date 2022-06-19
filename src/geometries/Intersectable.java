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
	private BoundingRegion bRegion = null;
	private boolean useBoundingRegion = true;

	/**
	 * get bounding region of geometry/list of geometries
	 * 
	 * @return bounding region
	 */
	protected abstract BoundingRegion getBoundingRegion();

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
			if (other == null || !(other instanceof GeoPoint))
				return false;
			GeoPoint geoPoint = (GeoPoint) other;
			return geometry == geoPoint.geometry && point.equals(geoPoint.point);
		}

		@Override
		public String toString() {
			return "GP: " + point + " in " + geometry;
		}
	}

	/**
	 * returns the list of intersection points of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * returns the list of intersection geopoints of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		getBR();
		if (bRegion != null)
			if (!bRegion.isThereIntersection(ray))
				return null;
		return findGeoIntersectionsHelper(ray);
	}

	/**
	 * returns the list of intersection geopoints of a ray with geometry(ies)
	 * 
	 * @param ray the ray
	 * @return list of intersection points
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

	/**
	 * find the closest intersection of the ray
	 * 
	 * @param ray the ray
	 * @return the closest intersection if there is any, else null
	 */
	public GeoPoint findClosestIntersection(Ray ray) {
		return ray.findClosestGeoPoint(findGeoIntersections(ray));
	}

	/**
	 * get the bounding region
	 * 
	 * @return the bounding region
	 */
	public BoundingRegion getBR() {
		if (bRegion == null && useBoundingRegion)
			bRegion = getBoundingRegion();
		return bRegion;
	}

	/**
	 * setter for using/not using Bounding Region
	 * 
	 * @param b using/not using Bounding Region (true=using)
	 * @return the object
	 */
	public Intersectable setUseBoundingRegion(boolean b) {
		useBoundingRegion = b;
		return this;
	}

}
