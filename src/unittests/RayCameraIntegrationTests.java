package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
 
import renderer.Camera;
import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author ori frankel and yair sprecher
 *
 */
class RayCameraIntegrationTests {
	private int countIntersections(Intersectable geometries,Camera camera,int nx,int ny) {
		int count=0;
		for(int i=0;ny>i;++i)
			for(int j=0;nx>j;++j)
				count+=geometries.findIntersections(camera.constructRay(nx, ny, j, i)).size();
		return count;
	}
	static final Point ZERO_POINT = new Point(0, 0, 0);
	/**
	 * Test method for the integration between {@link geometries.Sphere#findIntersections(primitives.Ray)} and
	 * {@link renderer.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void TestConstructRayAndSphereFindIntersections() {
		//TC01 - 2 intersection points
		Camera camera=new Camera(ZERO_POINT,new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(1).setVPSize(3, 3);
		Intersectable geometries=new Geometries(new Sphere(1,new Point(0, 0, -3)));
		assertEquals(2, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC02 - 18 intersection points
		geometries=new Geometries(new Sphere(2.5,new Point(0, 0, -2.5)));
		camera.setLocation(new Point(0, 0, 0.5));
		assertEquals(18, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC03 - 10 intersection points
		geometries=new Geometries(new Sphere(2,new Point(0, 0, -2)));
		assertEquals(10, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC04 - 9 intersection points
		geometries=new Geometries(new Sphere(4,new Point(0, 0, -1)));
		assertEquals(9, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC05 - 0 intersection points
		geometries=new Geometries(new Sphere(0.5,new Point(0, 0, 1)));
		assertEquals(0, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
	}
	
	/**
	 * Test method for the integration between {@link geometries.Plane#findIntersections(primitives.Ray)} and
	 * {@link renderer.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void TestConstructRayAndPlaneFindIntersections() {
		//TC01 - 9 intersection points
		Camera camera=new Camera(ZERO_POINT,new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(1).setVPSize(3, 3);
		Intersectable geometries=new Geometries(new Plane(new Point(0,0,-2),new Vector(0, 0, -1)));
		assertEquals(9, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC02 - 9 intersection points
		geometries=new Geometries(new Plane(new Point(0,0,-5),new Vector(0, -1, -2)));
		assertEquals(9, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC03 - 6 intersection points
		geometries=new Geometries(new Plane(new Point(0,0,-5),new Vector(0, -1, -1)));
		assertEquals(6, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
	}
	
	/**
	 * Test method for the integration between {@link geometries.Triangle#findIntersections(primitives.Ray)} and
	 * {@link renderer.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void TestConstructRayAndTriangleFindIntersections() {
		//TC01 - 1 intersection point
		Camera camera=new Camera(ZERO_POINT,new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(1).setVPSize(3, 3);
		Intersectable geometries=new Geometries(new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2)));
		assertEquals(1, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
		
		//TC02 - 2 intersection points
		geometries=new Geometries(new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2)));
		assertEquals(2, countIntersections(geometries,camera,3,3),"wrong number of intersection points");
	}

}
