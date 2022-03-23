package unittests;
 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Testing Cylinder
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class CylinderTest {
	/**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
	@Test
	void testGetNormal() {
    	// ============ Equivalence Partitions Tests ==============
		//TC01: the point is on surface
		Point begin = new Point(0, 0, 0);
		Vector direction = new Vector(1, 0, 0);
		Ray ray = new Ray(begin, direction);
		Cylinder cylinder = new Cylinder(ray, 1, 2);
		Point p = new Point(1, 1, 0);
		Vector v = new Vector(0, 1, 0);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is on the surface");
		//TC02: the point is on the first base
		v = new Vector(-1, 0, 0);
		p = new Point(0, 0, 0.5);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is on the first base");
		//TC03: the point is on the second base
		v = new Vector(1, 0, 0);
		p = new Point(2, 0, 0.5);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is on the second base");
        // =============== Boundary Values Tests ==================
        //TC10: the point is at the center of the first base
		v = new Vector(-1, 0, 0);
		p = new Point(0, 0, 0);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is at the center of the first base");
		//TC11: the point is at the center of the second base
		v = new Vector(1, 0, 0);
		p = new Point(2, 0, 0);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is at the center of the second base");
		//TC12: the point is on the edge of the first base
		v=new Vector(-1,0,0);
		p=new Point(0,1,0);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is on the edge of the first base");
		//TC12: the point is on the edge of the second base
		v=new Vector(1,0,0);
		p=new Point(2,1,0);
		assertEquals(cylinder.getNormal(p), v, "Cylinder getNormal() wrong result when the point is on the edge of the second base");
	}
}
