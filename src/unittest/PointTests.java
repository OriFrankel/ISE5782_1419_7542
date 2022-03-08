/**
 * 
 */
package unittest;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * Tests for the class Point
 * @author Ori Frankel, Yair Sprecher
 *
 */
class PointTests {
	/**
     * Test method for {@link primitives.Point#add(primitives.Point)}.
     */
    @Test
    public void testAdd() {
    	// ============ Equivalence Partitions Tests ==============
    	// some vector and some point
    	Vector v1 = new Vector(1, 2, 3);
    	Point p1 = new Point(1, 0, 2);
    	Point p2 = new Point(2, 2, 5);
    	assertEquals(p1.add(v1), p2, "point add wrong result");
        // =============== Boundary Values Tests ==================
        // the sum equals 0
    	v1 = new Vector(2, -7, 3);
    	p1 = new Point(-2, 7, -3);
    	p2 = new Point(0, 0, 0);
    	assertEquals(p1.add(v1), p2, "point add wrong result when the sum is zero");
    }
	/**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    public void testSubstract() {
    	// ============ Equivalence Partitions Tests ==============
    	// some point and some point
    	Point p1 = new Point(1, 2, 3);
    	Point p2 = new Point(1, 0, 2);
    	Vector v1 = new Vector(0, 2, 1);
    	assertEquals(p1.subtract(p2), v1, "point subtract wrong result");
        // =============== Boundary Values Tests ==================
        // the difference equals 0
    	assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "point subtract wrong result when the difference is zero");
    }
}