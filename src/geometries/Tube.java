package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
public class Tube implements Geometry {
	Ray axisRay;
	double radius;
	public Tube(Ray ray, double d) {
		axisRay = ray;
		radius = d;
	}
	@Override
	public Vector getNormal(Point p) {
		return null;
	}
	public Ray getAxisRay() {
		return axisRay;
	}
	public double getRadius() {
		return radius;
	}
	@Override
	public String toString() {
		return axisRay.toString() + " and radius: " + ((Double)radius).toString();
	}
}