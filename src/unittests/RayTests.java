
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
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

}
