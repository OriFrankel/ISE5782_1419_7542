package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.BoundingRegion;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class BoundingRegionTests {

	@Test
	void testIsThereIntersection() {
		BoundingRegion br=new BoundingRegion(new Point(0, 1, 2), new Point(1, 2, 3),false);
		assertEquals(true, br.isThereIntersection(new Ray(new Point(0.5, 1.5, 2.5), new Vector(1, 1, 1))));
		br=new BoundingRegion(new Point(0, 1, 2), new Point(1, 2, 3),false);
		assertEquals(true, br.isThereIntersection(new Ray(new Point(0.5, 1.5, 2.5), new Vector(1, 1, 0))));
		br=new BoundingRegion(new Point(0, 1, 2), new Point(1, 2, 3),false);
		assertEquals(true, br.isThereIntersection(new Ray(new Point(0.5, 1.5, 2.5), new Vector(0, 1, 0))));
		br=new BoundingRegion(new Point(0, 1, 2), new Point(1, 2, 3),false);
		assertEquals(false, br.isThereIntersection(new Ray(new Point(-1, -1, -1), new Vector(1, 1, 0))));
		br=new BoundingRegion(new Point(0, 1, 2), new Point(1, 2, 3),false);
		assertEquals(false, br.isThereIntersection(new Ray(new Point(-1, -1, -1), new Vector(0, 1, 1))));
	}

}
