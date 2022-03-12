package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.Plane;
class PlaneTests {
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Point p1=new Point(1,2,3);
		Point p2=new Point(4,5,6);
		Point p3=new Point(7,8,9);
		Plane p=new Plane(p1,p2,p3);
		// ============ Equivalence Partitions Tests ==============
		//some point
		Vector v=p.getNormal(p1);
		assertEquals(v.dotProduct(p2.subtract(p1)),0,0.0000001,"getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.dotProduct(p3.subtract(p1)),0,0.0000001,"getNormal wrong result - doesn't orthogonal to vector in the plane");
		assertEquals(v.length(),1,0.0000001,"getNormal wrong result - length doesn't equal to 1");
	}

}
