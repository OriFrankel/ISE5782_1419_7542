package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * class for list of geometries
 * 
 * @author ori frankel, yair sprecher
 *
 */
public class Geometries extends Intersectable {
	private List<Intersectable> geometries = new LinkedList<>();

	/**
	 * constructor, set the list to the given elements
	 * 
	 * @param geometries the geometries
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * add new geometries to the list
	 * 
	 * @param newGeometry the new geometries
	 */
	public void add(Intersectable... newGeometry) {
		if (geometries == null)
			return;
		geometries.addAll(List.of(newGeometry));
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		if (geometries == null)
			return null;
		List<GeoPoint> list = null;
		for (Intersectable intersectable : geometries) {
			List<GeoPoint> l = intersectable.findGeoIntersections(ray);
			if (l != null) {
				if (list == null)
					list = new LinkedList<GeoPoint>(l);
				else
					list.addAll(l);
			}
		}
		return list;
	}
}