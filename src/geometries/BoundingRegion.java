package geometries;

import primitives.*;

/**
 * bounding region for geometry
 * 
 * @author ori frankel, yair sprecher
 *
 */
public class BoundingRegion {
	private Point mins;
	private Point maxes;
	private boolean flag=false;
	/**
	 * constructor,gets the minimum and maximum value for each coordinate
	 * 
	 * @param mins  minimum values
	 * @param maxes maximum values
	 */
	public BoundingRegion(Point mins, Point maxes,boolean flag) {
		this.mins = mins;
		this.maxes = maxes;
		this.flag=flag;
	}
	private double f1(double a,double b,double c) {
		try {
			return (a - b) / c;
			
		} catch (ArithmeticException e) {
			return b>= a ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;

		}
	}
	/**
	 * find if the ray intersects the boundary region
	 * 
	 * @param ray the ray
	 * @return if the ray intersects the boundary region
	 */
	public boolean isThereIntersection(Ray ray) {
		if(flag)
			return true;
		double minx, miny, minz, maxx, maxy, maxz, temp;
		Point p = ray.getP0();
		Vector dir = ray.getDir();
		
		minx=f1(mins.getX(),p.getX(),dir.getX());
		maxx=f1(maxes.getX(),p.getX(),dir.getX());
		if(minx==Double.POSITIVE_INFINITY||maxx==Double.NEGATIVE_INFINITY)
			return false;
		if (minx > maxx) {
			temp = minx;
			minx = maxx;
			maxx = temp;
		}
		miny=f1(mins.getY(),p.getY(),dir.getY());
		maxy=f1(maxes.getY(),p.getY(),dir.getY());
		if(miny==Double.POSITIVE_INFINITY||maxy==Double.NEGATIVE_INFINITY)
			return false;
		if (miny > maxy) {
			temp = miny;
			miny = maxy;
			maxy = temp;
		}
		minz=f1(mins.getZ(),p.getZ(),dir.getZ());
		maxz=f1(maxes.getZ(),p.getZ(),dir.getZ());
		if(minz==Double.POSITIVE_INFINITY||maxz==Double.NEGATIVE_INFINITY)
			return false;
		if (minz > maxz) {
			temp = minz;
			minz = maxz;
			maxz = temp;
		}
		
		return Math.min(maxz,Math.min(maxx, maxy))>=Math.max(0,Math.max(minz,Math.max(minx, miny)));
	}
}