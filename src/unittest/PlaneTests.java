package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.Plane;

/**
 * Testing Plane
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class PlaneTests {
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Point p1=new Point(1,2,3);
		Point p2=new Point(4,5,6);
		Point p3=new Point(37,52,11);
		Plane p=new Plane(p1,p2,p3);
		// ============ Equivalence Partitions Tests ==============
		//TC00: some point
		Vector v=p.getNormal(p1);
		assertEquals(v.dotProduct(p2.subtract(p1)),0,0.0000001,"Plane getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.dotProduct(p3.subtract(p1)),0,0.0000001,"Plane getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.length(),1,0.0000001,"Plane getNormal wrong result - length doesn't equal to 1");
	}
	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point,primitives.Point,primitives.Point)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: some three points
		Point p1=new Point(1,2,3);
		Point p2=new Point(4,5,6);
		Point p3=new Point(37,52,11);
		Plane p=new Plane(p1,p2,p3);
		Vector v=p.getNormal();
		Point point=p.getP0();
		try {
		assertEquals(v.dotProduct(point.subtract(p1)),0,0.0000001,"wrong plane constructor - first point is not on the plane");
		} catch (IllegalArgumentException e) {
			
		}
		assertEquals(v.dotProduct(point.subtract(p2)),0,0.0000001,"wrong plane constructor - second point is not on the plane");
		assertEquals(v.dotProduct(point.subtract(p3)),0,0.0000001,"wrong plane constructor - third point is not on the plane");
		assertEquals(v.length(),1,0.0000001,"wrong plane constructor - normal length is not 1");
		// =============== Boundary Values Tests ==================
		//TC10: two points are equal
		assertThrows(IllegalArgumentException.class, () -> new Plane(p1,p1,p3), "there is no exception when two points are equal");
		//TC11: three point on a line
		Point p4=new Point(7,8,9);
		assertThrows(IllegalArgumentException.class, () -> new Plane(p1,p2,p4), "there is no exception when three points are on one line");
	}
	
	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		// ============ Equivalence Partitions Tests ==============
		Plane plane = new Plane(new Point(0, 1, 0), new Vector(0, 1, 0));
		//TC01: Ray intersects the plane
		Ray ray = new Ray(new Point(0.5, 0.5, 0), new Vector(1, 1, 0));
		List<Point> list = plane.findIntersections(ray);
		assertEquals(1, list.size(), "wrong number of points");
		assertEquals(new Point(1, 1, 0), list.get(0), "wrong point");
		
		//TC02: Ray doesn't intersect the plane
		ray = new Ray(new Point(0.5, 0.5, 0), new Vector(-1, -1, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		// =============== Boundary Values Tests ==================
		//TC10: Ray is parallel to plane but not included in it
		ray = new Ray(new Point(1, 0, 0), new Vector(1, 0, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		//TC11: Ray is included in the plane
		ray = new Ray(new Point(1, 1, 0), new Vector(1, 0, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		//TC12: Ray is orthogonal to the plane and it start before it
		ray = new Ray(new Point(0, 0.5, 0), new Vector(0, 1, 0));
		list = plane.findIntersections(ray);
		assertEquals(1, list.size(), "wrong number of points");
		assertEquals(new Point(0, 1, 0), list.get(0), "wrong point");
		
		//TC13: Ray is orthogonal to the plane and it start inside it
		ray = new Ray(new Point(1, 1, 0), new Vector(0, 1, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		//TC14: Ray is orthogonal to the plane and it start after it
		ray = new Ray(new Point(1, 2, 0), new Vector(0, 1, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		//TC15: Ray begins on the plane
		ray = new Ray(new Point(1, 1, 0), new Vector(1, 1, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
		
		//TC16: Ray begins on reference point of the plane
		ray = new Ray(new Point(0, 1, 0), new Vector(1, 1, 0));
		assertNull(plane.findIntersections(ray), "wrong number of points");
	}
}