package unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Vector;
/**
 * Testing Sphere
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class SphereTest {
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: some point
		double radius = 2;
		Point center = new Point(0, 0, 0);
		Point p = new Point(2, 0, 0);
		Vector v = new Vector(1, 0, 0);
		Sphere sphere = new Sphere(center, radius);
		assertEquals(sphere.getNormal(p), v, "Sphere getNormal wrong result");
	}
}
