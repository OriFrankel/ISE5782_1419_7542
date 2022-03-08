package geometries;

import primitives.Point;

/**
 * triangle on the space
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Triangle extends Polygon {
	/**
	 * constructor for triangle gets 3 points ,the vertices of the triangle
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}
}