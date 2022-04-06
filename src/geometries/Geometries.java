package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point;
import primitives.Ray;

public class Geometries extends Intersectable {
	private final List<Intersectable> geometries;

	public Geometries() {
		geometries = new LinkedList<Intersectable>();
	}

	public Geometries(Intersectable... geometries) {
		this.geometries = new LinkedList<Intersectable>(Arrays.asList(geometries));
		;
	}

	public void add(Intersectable... newGeometry) {
		for (Intersectable i : newGeometry) {
			geometries.add(i);
		}
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		if (geometries == null)
			return null;
		List<GeoPoint> list = new LinkedList<GeoPoint>();
		for (Intersectable intersectable : geometries) {
			List<GeoPoint> l = intersectable.findGeoIntersections(ray);
			if (l != null)
				list.addAll(l);
		}
		if(list.size()==0)return null;
		return list;
	}
}