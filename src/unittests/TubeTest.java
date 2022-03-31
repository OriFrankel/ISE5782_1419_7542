package unittests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.Tube;

/**
 * Testing Tube
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class TubeTest {
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: some Tube and some point on it
		Point begin = new Point(0, 0, 0);
		Vector direction = new Vector(1, 0, 0);
		Ray ray = new Ray(begin, direction);
		Tube tube = new Tube(ray, 1);
		Point p = new Point(1, 1, 0);
		Vector v = new Vector(0, 1, 0);
		assertEquals(tube.getNormal(p), v, "Tube getNormal() wrong result");
		// =============== Boundary Values Tests ==================
		// TC10: the line from point on the surface to the start of the ray is
		// perpendicular to the ray
		p = new Point(0, 1, 0);
		assertEquals(tube.getNormal(p), v,
				"Tube getNormal() wrong result when the point on the surface is perpendicular to the ray");
	}
}
