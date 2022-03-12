package unittest;

import static org.junit.jupiter.api.Assertions.*;
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
		//some point
		Vector v=p.getNormal(p1);
		assertEquals(v.dotProduct(p2.subtract(p1)),0,0.0000001,"Plane getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.dotProduct(p3.subtract(p1)),0,0.0000001,"Plane getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.length(),1,0.0000001,"Plane getNormal wrong result - length doesn't equal to 1");
	}
	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point,primitives.Point,primitives.Point)}.
	 */
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		//some three points
		Point p1=new Point(1,2,3);
		Point p2=new Point(4,5,6);
		Point p3=new Point(37,52,11);
		Plane p=new Plane(p1,p2,p3);
		Vector v=p.getNormal();
		Point point=p.getP0();
		assertEquals(v.dotProduct(point.subtract(p1)),0,0.0000001,"wrong plane constructor - first point is not on the plane");
		assertEquals(v.dotProduct(point.subtract(p2)),0,0.0000001,"wrong plane constructor - second point is not on the plane");
		assertEquals(v.dotProduct(point.subtract(p3)),0,0.0000001,"wrong plane constructor - third point is not on the plane");
		assertEquals(v.length(),1,0.0000001,"wrong plane constructor - normal length is not 1");
		// =============== Boundary Values Tests ==================
		//two points are equal
		assertThrows(IllegalArgumentException.class, () -> new Plane(p1,p1,p3), "there is no exception when two points are equal");
		//three point on a line
		Point p4=new Point(7,8,9);
		assertThrows(IllegalArgumentException.class, () -> new Plane(p1,p2,p4), "there is no exception when three points are on one line");
	}

}
