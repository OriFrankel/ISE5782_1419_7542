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
	private List<Intersectable> geometries = null;

	public Geometries() {
	}

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
			geometries = new LinkedList<>();
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

	@Override
	protected BoundingRegion getBoundingRegion() {
		if (geometries == null)
			return null;
		BoundingRegion br = geometries.get(0).getBR();
		for (Intersectable intersectable : geometries) {
			br = br.union(intersectable.getBR());
		}
		return br;
	}

	/**
	 * make bvh from a list of geometries
	 * 
	 * @return the object itself
	 */
	public Geometries makeBVH() {
		if (geometries.size() <= 3)
			return this;
		Geometries g1 = new Geometries(geometries.get(0)), g2 = new Geometries(geometries.get(geometries.size() - 1)),
				g3 = new Geometries();
		for (int i = 1; geometries.size() - 1 > i; ++i)
			if (geometries.get(i).getBR().getFlag())
				g3.add(geometries.get(i));
			else if (geometries.get(i).getBR().getMins()
					.distanceSquared(geometries.get(0).getBR().getMins()) < geometries.get(i).getBR().getMins()
							.distanceSquared(geometries.get(geometries.size() - 1).getBR().getMins()))
				g1.add(geometries.get(i));
			else
				g2.add(geometries.get(i));

		g1.makeBVH();
		g2.makeBVH();
		geometries = new LinkedList<Intersectable>();
		geometries.add(g1);
		geometries.add(g2);
		if (g3.geometries != null)
			geometries.add(g3);
		/*
		 * while(geometries.size()>2) { int ind1=0,ind2=0; double
		 * minD2=Double.POSITIVE_INFINITY; for(int i=0;geometries.size()>i;++i) for(int
		 * j=0;i>j;++j){ double curD2=geometries.get(i).getBR().getMins().
		 * distanceSquared(geometries.get(j).getBR().getMins()); if(curD2<minD2) {
		 * minD2=curD2; ind1=i; ind2=j; } } Geometries newG=new
		 * Geometries(geometries.get(ind1),geometries.get(ind2));
		 * geometries.remove(ind1); geometries.remove(ind2); geometries.add(newG);
		 * 
		 * }
		 */
		return this;
	}
	public Intersectable setUseBoundingRegion(boolean b) {
		super.setUseBoundingRegion(b);
		for(Intersectable i:geometries)
			i.setUseBoundingRegion(b);
		return this;
	}
}