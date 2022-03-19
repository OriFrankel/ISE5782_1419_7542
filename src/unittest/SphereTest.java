package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Ray;
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
		Sphere sphere = new Sphere( radius,center);
		assertEquals(sphere.getNormal(p), v, "Sphere getNormal wrong result");
	}
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point (1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                   "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (!(result.get(0).equals(p1)))
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Sphere sphere2 = new Sphere(1.7320508075688772935274463, new Point (3, 0, 0));// radius:sqrt(3)
        Ray ray=new Ray(new Point(2.5,0.5,0.5),new Vector(3, 1, 1));
        result=sphere2.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        p1=new Point(4, 1, 1);
        assertEquals(List.of(p1), result, "wrong point");
        
        // TC04: Ray starts after the sphere (0 points)
        ray=new Ray(new Point(5,1,0),new Vector(1, 0, 0));
        assertNull(sphere2.findIntersections(ray), "Ray starts after the point");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        ray=new Ray(new Point(2,1,1),new Vector(1,0,0));
        result=sphere2.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "wrong point");
        
        // TC12: Ray starts at sphere and goes outside (0 points)
        ray=new Ray(p1,new Vector(1,0,0));
        assertNull(sphere2.findIntersections(ray), "Ray starts at sphere and goes outside");
        
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p2=new Point(2,-1,-1);
        ray=new Ray(new Point(1,-2,-2),new Vector(1,1,1));
        result=sphere2.findIntersections(ray);
        assertEquals(2, result.size(), "Wrong number of points");
        if (!(result.get(0).equals(p1)))
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere and goes through the center");
        
        // TC14: Ray starts at sphere and goes inside (1 points)
        ray=new Ray(new Point(2,-1,-1),new Vector(1,1,1));
        result=sphere2.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray starts at sphere and goes inside");
        
        // TC15: Ray starts inside (1 points)
        ray=new Ray(new Point(3.5,0.5,0.5),new Vector(1, 1, 1));
        result=sphere2.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray starts inside");
        
        // TC16: Ray starts at the center (1 points)
        ray=new Ray(new Point(3,0,0),new Vector(1, 1, 1));
        result=sphere2.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray starts at the center");
        
        // TC17: Ray starts at sphere and goes outside (0 points)
        ray=new Ray(p1,new Vector(1, 1, 1));
        assertNull(sphere2.findIntersections(ray), "Ray starts at sphere and goes outside");
        
        // TC18: Ray starts after sphere (0 points)
        ray=new Ray(new Point(5, 2, 2),new Vector(1, 1, 1));
        assertNull(sphere2.findIntersections(ray), "Ray starts after sphere");
        
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        Vector tangent =new Vector(-1, -1, 2);
        ray=new Ray(new Point(5, 2, -1),tangent);
        assertNull(sphere2.findIntersections(ray), "Ray starts before the tangent point");
        
        // TC20: Ray starts at the tangent point
        ray=new Ray(new Point(4,1,1),tangent);
        assertNull(sphere2.findIntersections(ray), "Ray starts at the tangent point");
        
        // TC21: Ray starts after the tangent point
        ray=new Ray(new Point(3,0,3),tangent);
        assertNull(sphere2.findIntersections(ray), "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        ray=new Ray(new Point(6,0,0),new Vector(0, 0, 1));
        assertNull(sphere2.findIntersections(ray),
        		"Ray's line is outside, ray is orthogonal to ray start to sphere's center line");
    }

}
