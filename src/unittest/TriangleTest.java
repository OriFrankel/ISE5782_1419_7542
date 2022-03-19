package unittest;

import static org.junit.jupiter.api.Assertions.*;
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
}
