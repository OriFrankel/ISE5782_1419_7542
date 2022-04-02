package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable {
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
	public List<Point> findIntersections(Ray ray) {
		if (geometries == null)
			return null;
		List<Point> list = new LinkedList<Point>();
		for (Intersectable intersectable : geometries) {
			List<Point> l = intersectable.findIntersections(ray);
			if (l != null)
				list.addAll(l);
		}
		return list;
	}
}