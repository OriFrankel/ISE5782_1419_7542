package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
	Point center;
	double radius;
	public Sphere(Point center,double radius) {
		this.center=center;
		this.radius=radius;
	}
	@Override
	public Vector getNormal(Point p) {
		
		return null;
	}
	public Point getCenter() {
		return center;
	}
	public double getRadius() {
		return radius;
	}
	@Override
	public String toString() {
		return "Sphere with center " + center.toString() + " and radius " + ((Double)radius).toString();
	}
}