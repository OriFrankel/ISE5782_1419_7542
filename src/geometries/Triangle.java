package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;

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
	@Override
	public List<Point> findIntersections(Ray ray){
		List<Point> list = plane.findIntersections(ray);
		if (list == null)
			return null;
		Point point = list.get(0);
		Point p0 = vertices.get(0);
		Point p1 = vertices.get(1);
		Point p2 = vertices.get(2);
		double x = point.getX() - p0.getX();
		double y = point.getY() - p0.getY();
		double x1 = p1.getX() - p0.getX();
		double y1 = p1.getY() - p0.getY();
		double x2 = p2.getX() - p0.getX();
		double y2 = p2.getY() - p0.getY();
		double det = x1 * y2 - y1 * x2;
		if(Util.isZero(det)) {
			x = point.getZ() - p0.getZ();
			y = point.getY() - p0.getY();
			x1 = p1.getZ() - p0.getZ();
			y1 = p1.getY() - p0.getY();
			x2 = p2.getZ() - p0.getZ();
			y2 = p2.getY() - p0.getY();
			det = x1 * y2 - y1 * x2;
		}
		if(Util.isZero(det)) {
			x = point.getZ() - p0.getZ();
			y = point.getX() - p0.getX();
			x1 = p1.getX() - p0.getX();
			y1 = p1.getX() - p0.getX();
			x2 = p2.getZ() - p0.getZ();
			y2 = p2.getX() - p0.getX();
			det = x1 * y2 - y1 * x2;
		}
		double a = (x*y2-y*x2)/det;
		double b = (x1*y-x*y1)/det;
		if (Util.alignZero(a) > 0 && Util.alignZero(b) > 0 && Util.alignZero(1-a-b) > 0)
			return list;
		return null;
	}
}