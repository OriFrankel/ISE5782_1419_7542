package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;


import geometries.*;
import primitives.*;

/**
 * tests for class geometries
 * @author ori frankel and yair sprecher
 *
 */
class GeometriesTests {
	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		Geometries shapes=new Geometries(new Plane(new Point(0,0,0),new Vector(1,0,0)),
				new Sphere(1d,new Point(0, 0, 0)),
				new Triangle(new Point(1, 0, 0),new Point(1, 1, 0),new Point(1, 0, 1)));
		Geometries emptyGeometries=new Geometries();
		// ============ Equivalence Partitions Tests ==============
		//TC01:some of the geometries intersect the ray
		List<Point>result =shapes.findIntersections(new Ray(new Point(-2, -0.5, -0.5), new Vector(1, 0, 0)));
		assertEquals(3,result.size(),"wrong number of points");
		// =============== Boundary Values Tests ==================
		//TC10: empty geometries list
		result =emptyGeometries.findIntersections(new Ray(new Point(-2, -0.5, -0.5), new Vector(1, 0, 0)));
		try {
		assertEquals(0, result.size(),"wrong number of points");
		} catch(NullPointerException e) {}
		//TC11: no geometry intersects the ray
		result =shapes.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 1, 0)));
		assertEquals(0,result.size(),"wrong number of points");
		//TC12: only one geometry intersect the ray
		result =shapes.findIntersections(new Ray(new Point(-2, -3, -3), new Vector(1, 0, 0)));
		assertEquals(1, result.size(),"wrong number of points");
		//TC12: all the geometries intersect the ray
		result =shapes.findIntersections(new Ray(new Point(-2, 0.25, 0.25), new Vector(1, 0, 0)));
		assertEquals(4, result.size(),"wrong number of points");
	}


}
