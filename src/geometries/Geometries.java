package geometries;

import java.util.Arrays;
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
	private List<Intersectable> geometries;

	/**
	 * constructor, set the list to null
	 */
	public Geometries() {
		geometries = null;
	}
	/**
	 * constructor, set the list to the given elements
	 * @param geometries the geometries
	 */
	public Geometries(Intersectable... geometries) {
		this.geometries = new LinkedList<Intersectable>(Arrays.asList(geometries));
	}
	/**
	 * add new geometries to the list
	 * @param newGeometry the new geometries
	 */
	public void add(Intersectable... newGeometry) {
		if(geometries==null)this.geometries = new LinkedList<Intersectable>(Arrays.asList(newGeometry));
		
		else for (Intersectable i : newGeometry) {
			geometries.add(i);
		}
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