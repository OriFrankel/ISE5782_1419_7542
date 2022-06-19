package geometries;

import primitives.*;
import static primitives.Util.*;
/**
 * bounding region for geometry
 * 
 * @author ori frankel, yair sprecher
 *
 */
public class BoundingRegion {
	/**
	 * one point on the bounding region, minimum in each coordinate
	 */
	private Point mins;
	/**
	 * one point on the bounding region, maximum in each coordinate
	 */
	private Point maxes;
	/**
	 * true if the Bounding Region is infinite
	 */
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
	private static double  f1(double a,double b,double c,boolean ismax) {
		try {
			return (a - b) / c;
			
		} catch (ArithmeticException e) {
			return (((b>= a)&&ismax)||(a>=b&&!ismax) )? Double.NEGATIVE_INFINITY: Double.POSITIVE_INFINITY;

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
		if(isZero(dir.getX())||isZero(dir.getY())||isZero(dir.getZ()))
			return true;
		minx=f1(mins.getX(),p.getX(),dir.getX(),false);
		maxx=f1(maxes.getX(),p.getX(),dir.getX(),true);
		if (dir.getX()<0) {
			temp = minx;
			minx = maxx;
			maxx = temp;
		}
		miny=f1(mins.getY(),p.getY(),dir.getY(),false);
		maxy=f1(maxes.getY(),p.getY(),dir.getY(),true);
		if (dir.getY()<0) {
			temp = miny;
			miny = maxy;
			maxy = temp;
		}
		minz=f1(mins.getZ(),p.getZ(),dir.getZ(),false);
		maxz=f1(maxes.getZ(),p.getZ(),dir.getZ(),true);
		if (dir.getZ()<0) {
			temp = minz;
			minz = maxz;
			maxz = temp;
		}
		return alignZero(Math.min(maxz,Math.min(maxx, maxy))-Math.max(0,Math.max(minz,Math.max(minx, miny))))>=-100;
	}
	/**
	 * union of two Bounding Regions 
	 * @param other other Bounding Region
	 * @return the union
	 */
	public BoundingRegion union(BoundingRegion other) {
		if(flag||other.flag)
			return new BoundingRegion(null,null,true);
		return new BoundingRegion(
				new Point(Math.min(mins.getX(),other.mins.getX()),
						Math.min(mins.getY(),other.mins.getY()),
						Math.min(mins.getZ(),other.mins.getZ())),
				new Point(Math.max(maxes.getX(),other.maxes.getX()),
						Math.max(maxes.getY(),other.maxes.getY()),
						Math.max(maxes.getZ(),other.maxes.getZ())),false);
	}
	/**
	 * get a corner of the BR
	 * @return some corner
	 */
	public Point getMins() {
		return mins;
	}
	public boolean getFlag(){
		return flag;
	}
}