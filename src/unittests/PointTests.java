/**
 * 
 */
package unittests;

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
    	//TC01: some vector and some point
    	Vector v1 = new Vector(1, 2, 3);
    	Point p1 = new Point(1, 0, 2);
    	Point p2 = new Point(2, 2, 5);
    	assertEquals(p1.add(v1), p2, "point add wrong result");
        // =============== Boundary Values Tests ==================
        //TC10: the sum equals 0
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
    	//TC01: some point and some point
    	Point p1 = new Point(1, 2, 3);
    	Point p2 = new Point(1, 0, 2);
    	Vector v1 = new Vector(0, 2, 1);
    	assertEquals(p1.subtract(p2), v1, "point subtract wrong result");
        // =============== Boundary Values Tests ==================
        //TC10: the difference equals 0
    	assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "point subtract wrong result when the difference is zero");
    }
    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    public void testDistanceSquared() {
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: some point and some point
    	Point p1 = new Point(1, 2, 3);
    	Point p2 = new Point(1, 0, 2);
    	assertEquals(p1.distanceSquared(p2),5,0.0000000000001, "point distanceSquared wrong result");
        // =============== Boundary Values Tests ==================
        //TC10: equal points
    	assertEquals(p1.distanceSquared(p1),0,0.0000000000001, "point distanceSquared wrong result when points are equal");
    	
    }
    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    public void testDistance() {
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: some point and some point
    	Point p1 = new Point(3, 2, 3);
    	Point p2 = new Point(1, 0, 2);
    	assertEquals(p1.distance(p2),3,0.0000000000001, "point distance wrong result");
        // =============== Boundary Values Tests ==================
        //TC10: equal points
    	assertEquals(p1.distanceSquared(p1),0,0.0000000000001, "point distance wrong result when points are equal");
    }
}