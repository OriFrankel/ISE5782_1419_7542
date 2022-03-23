package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Util;
import primitives.Vector;

/**
 * Testing Vector
 * 
 * @author Ori Frankel, Yair Sprecher
 *
 */
class VectorTest {
	/**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
	@Test
	void testAddVector() {
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: some vector and some vector
    	Vector v1 = new Vector(1, 2, 3);
    	Vector v2 = new Vector(1, 0, 2);
    	Vector v3 = new Vector(2, 2, 5);
    	assertEquals(v2.add(v1), v3, "Vector add wrong result");
    	
        // =============== Boundary Values Tests ==================
        //TC10: the sum equals 0
    	Vector v4 = new Vector(2, -7, 3);
    	Vector v5 = new Vector(-2, 7, -3);
    	assertThrows(IllegalArgumentException.class, () -> v4.add(v5), "Vector add wrong result when the sum is zero");
	}
	/**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
	@Test
	void testScale() {
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: some vector and a scalar < -1
    	Vector v1 = new Vector(1, 2, 3);
    	Vector v2 = new Vector(-2, -4, -6);
    	assertEquals(v1.scale(-2), v2, "Vector scale wrong result when: scalar < -1");
    	//TC02: some vector and a -1 < scalar < 0
    	v2 = new Vector(-0.5, -1, -1.5);
    	assertEquals(v1.scale(-0.5), v2, "Vector scale wrong result when: -1 < scalar < 0");
    	//TC03: some vector and a 0 < scalar < 1
    	v2 = new Vector(0.5, 1, 1.5);
    	assertEquals(v1.scale(0.5), v2, "Vector scale wrong result when: 0 < scalar < 1");
    	//TC04: some vector and a 1 < scalar
    	v2 = new Vector(2, 4, 6);
    	assertEquals(v1.scale(2), v2, "Vector scale wrong result when: 1 < scalar");
        // =============== Boundary Values Tests ==================
        //TC10: the scalar equals 0    	
    	assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "Vector scale wrong result when the scalar is zero");
        //TC11: the scalar equals -1
    	v2 = new Vector(-1, -2, -3);
    	assertEquals(v1.scale(-1), v2, "Vector scale wrong result when: -1 == scalar");
        //TC12: the scalar equals 1
    	assertEquals(v1.scale(1), v1, "Vector scale wrong result when: 1 == scalar");
	}
	/**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
	@Test
	void testDotProduct() {
    	// ============ Equivalence Partitions Tests ==============
		//TC01: Acute angle
    	Vector v1 = new Vector(1, 2, 3);
    	Vector v2 = new Vector(2, 4, 7);
    	assertEquals(v1.dotProduct(v2), 31, 0.000000000001, "Vector dotProduct wrong result when the angle is an acute angle");
		//TC02: Obtuse angle
    	v2 = new Vector(-4, -2, -9);
    	assertEquals(v1.dotProduct(v2), -35, 0.000000000001, "Vector dotProduct wrong result when the angle is an obtuse angle");
        // =============== Boundary Values Tests ==================
		//TC10: Right angle
    	v2 = new Vector(0, -3, 2);
    	assertEquals(v1.dotProduct(v2), 0, 0.000000000001, "Vector dotProduct wrong result when the angle is a right angle");
	}

	/**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, -3, 2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.000000001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(Util.isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(Util.isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                     "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: length >1
		Vector v1=new Vector(1, 2, 3);
		assertEquals(v1.lengthSquared(), 14, 0.000000000001, "Vector lengthSquared wrong result when length>1");
		//TC02: length <1
		v1=new Vector(0.1, 0.2, 0.3);
		assertEquals(v1.lengthSquared(), 0.14, 0.000000000001, "Vector lengthSquared wrong result when length<1");
		// =============== Boundary Values Tests ==================
		//TC10: length =1
		v1=new Vector(0, 0, 1);
		assertEquals(v1.lengthSquared(), 1, 0.000000000001, "Vector lengthSquared wrong result when length=1");
	}
    /**
     * Test method for {@link primitives.Vector#length()}.
     */
	@Test
	void testLength() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: length >1
		Vector v1=new Vector(1, 2, 2);
		assertEquals(v1.length(), 3, 0.000000000001, "Vector length wrong result when length>1");
		//TC02: length <1
		v1=new Vector(0.1, 0.2, 0.2);
		assertEquals(v1.length(), 0.3, 0.000000000001, "Vector length wrong result when length<1");
		// =============== Boundary Values Tests ==================
		//TC10: length =1
		v1=new Vector(0, 0, 1);
		assertEquals(v1.length(), 1, 0.000000000001, "Vector length wrong result when length=1");
	}
	 /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
	@Test
	void testNormalize() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: normalize vector with length >1
		Vector v1=new Vector(0, 0, 5);
		Vector v2=new Vector(0, 0, 1);
		assertEquals(v1.normalize(), v2, "Vector normalize wrong result when length>1");
		//TC02: normalize vector with length <1
		v1=new Vector(0, 0, 0.2);
		assertEquals(v1.normalize(), v2, "Vector normalize wrong result when length<1");
		// =============== Boundary Values Tests ==================
		//TC10: normalize vector with length =1
		assertEquals(v2.normalize(), v2, "Vector normalize wrong result when length=1");
	}

}
