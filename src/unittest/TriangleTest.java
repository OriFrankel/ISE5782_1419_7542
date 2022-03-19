package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import primitives.*;
import geometries.Triangle;

import org.junit.jupiter.api.Test;

/**
 * Testing Triangle
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class TriangleTest {

	/**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
	@Test
	void testGetNormal() {
		Point p1=new Point(1,2,3);
		Point p2=new Point(4,5,6);
		Point p3=new Point(37,52,11);
		Triangle t=new Triangle(p1,p2,p3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: some Triangle
		Vector v=t.getNormal(p1);
		assertEquals(v.dotProduct(p2.subtract(p1)),0,0.0000001,"Triangle getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.dotProduct(p3.subtract(p1)),0,0.0000001,"Triangle getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.length(),1,0.0000001,"Triangle getNormal wrong result - length doesn't equal to 1");
	}
	/**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
	@Test
	void testFindIntersections() {
		Triangle t = new Triangle(new Point(1, 2, 0), new Point(5, 1, 0), new Point(6, 4, 0));
	    // ============ Equivalence Partitions Tests ==============
        // TC01: Point in the triangle
		Ray r = new Ray(new Point(4, 2, 1), new Vector(0, 0, -1));
		List<Point> list = t.findIntersections(r);
		assertEquals(1, list.size(), "wrong number of points");
		assertEquals(new Point(4, 2, 0), list.get(0), "Point in the triangle");
		
		// TC02: Point is in front of edge
		r = new Ray(new Point(3, 4, 1), new Vector(0, 0, -1));
		assertNull(t.findIntersections(r), "wrong number of points");
		
		// TC03: Point is in front of vertex
		r = new Ray(new Point(0, 2, 1), new Vector(0, 0, -1));
		assertNull(t.findIntersections(r), "wrong number of points");
		
        // =============== Boundary Values Tests ==================
        // TC11: Point is a vertex
		r = new Ray(new Point(0, 2, 1), new Vector(0, 0, -1));
		assertNull(t.findIntersections(r), "wrong number of points");
		
		// TC12: Point at a edge
		r = new Ray(new Point(3.5, 3, 1), new Vector(0, 0, -1));
		assertNull(t.findIntersections(r), "wrong number of points");
		
		// TC13: Point at a continuation of edge
		r = new Ray(new Point(7, 7, 1), new Vector(0, 0, -1));
		assertNull(t.findIntersections(r), "wrong number of points");
	}
}
