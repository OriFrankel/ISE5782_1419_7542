package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected final List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected final Plane plane;
	private final int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		size = vertices.length;

		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (size == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> list = plane.findGeoIntersections(ray);
		if (list == null)
			return null;
		Point point = list.get(0).point;
		Vector normal = plane.getNormal();
		try {
			double k = alignZero(vertices.get(1).subtract(vertices.get(0)).crossProduct(point.subtract(vertices.get(1)))
					.dotProduct(normal));
			if (k == 0)
				return null;
			for (int i = 1; size > i; ++i) {
				double k2 = alignZero(vertices.get((i + 1) % size).subtract(vertices.get(i))
						.crossProduct(point.subtract(vertices.get((i + 1) % size))).dotProduct(normal));
				if (alignZero(k2 * k) <= 0)
					return null;
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
		return List.of(new GeoPoint(this, point));
	}
	@Override
	protected BoundingRegion getBoundingRegion() {
		double minx = vertices.get(0).getX(), maxx = vertices.get(0).getX(),
				miny = vertices.get(0).getY(), maxy = vertices.get(0).getY(),
				minz = vertices.get(0).getZ(), maxz = vertices.get(0).getZ();
		for (Point i : vertices) {
			minx = Math.min(minx, i.getX());
			miny = Math.min(miny, i.getY());
			minz = Math.min(minx, i.getZ());
			maxx = Math.max(maxx, i.getX());
			maxy = Math.max(maxy, i.getY());
			maxz = Math.max(maxz, i.getZ());
		}
		return new BoundingRegion(new Point(minx, miny, minz), new Point(maxx, maxy, maxz), false);
	}
}
