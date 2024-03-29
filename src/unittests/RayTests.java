
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * testing the class Ray
 * @author ori frankel and yair sprecher
 *
 */
class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(0, 0, 1), new Vector(0, 1, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01 - scalar>0
		assertEquals(new Point(0, 2, 1), ray.getPoint(2), "wrong point in getpoint");
		// TC02 - scalar<0
		assertEquals(new Point(0, -2, 1), ray.getPoint(-2), "wrong point in getpoint");
		// =============== Boundary Values Tests ==================
		// TC10 - scalar=0
		assertEquals(ray.getP0(), ray.getPoint(0), "wrong point in getpoint");
	}

	/**
	 * Test method for
	 * {@link primitives.Ray#findClosestPoint(java.util.List<primitives.Point>)}.
	 */
	@Test
	void testFindClosestPoint() {
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01 - closest point in middle
		assertEquals(new Point(1, 0, 0),
				ray.findClosestPoint(List.of(new Point(2, 0, 0), new Point(1, 0, 0), new Point(3, 0, 0))),
				"wrong point");
		// =============== Boundary Values Tests ==================
		// TC10 - empty list
		assertNull(ray.findClosestPoint(null), "wrong answer");
		// TC11 - closest point in start
		assertEquals(new Point(1, 0, 0),
				ray.findClosestPoint(List.of(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0))),
				"wrong point");
		// TC12 - closest point in end
		assertEquals(new Point(1, 0, 0),
				ray.findClosestPoint(List.of(new Point(3, 0, 0), new Point(2, 0, 0), new Point(1, 0, 0))),
				"wrong point");
	}

}
