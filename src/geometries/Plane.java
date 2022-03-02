package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
	Point p0;
	Vector normal;
	public Plane(Point p0,Vector normal) {
		this.p0=p0;
		this.normal=normal.normalize();
	}
	public Plane(Point p1,Point p2,Point p3) {
		this(p1,p2.subtract(p1).crossProduct(p3.subtract(p1)));
	}
	
	public Vector getNormal() {
		// TODO Auto-generated method stub
		return normal;
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return getNormal();
	}
	public Point getP0() {
		return p0;
	}

}
