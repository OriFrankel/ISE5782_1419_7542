
package renderer;

import primitives.*;
import static primitives.Util.*;
/**
 * the camera for making pictures
 * @author ori frankel and yair sprecher
 */
public class Camera {
	private Point location;
	private Vector vto,vup,vright;
	double VPwidth,VPheight,VPdistance;
	/**
	 * Constructor for camera,gets location and direction vectors
	 * @param loc location of the camera
	 * @param vto vector from the camera to the view plane
	 * @param vup vector to the upper direction (must be orthogonal to vto)
	 */
	public Camera(Point loc,Vector vto,Vector vup) {
		if(!isZero(vto.dotProduct(vup)))
			throw new IllegalArgumentException("vectors must be orthogonal");
		location=loc;
		this.vto=vto.normalize();
		this.vup=vup.normalize();
		this.vright=vto.crossProduct(vup);
	}
	/**
	 * setter for width of view plane,height of view plane
	 * @param width the width
	 * @param height the height
	 * @return the resulting camera
	 */
	public Camera setVPSize(double width, double height) {
		VPwidth=width;
		VPheight=height;
		return this;
	}
	/**
	 * setter for distance of view plane
	 * @param distance the distance
	 * @return the resulting camera
	 */
	public Camera setVPDistance(double distance) {
		VPdistance=distance;
		return this;
	}
	/**
	 * construct ray from the camera through a pixel, given its place 
	 * @param nX amount of columns
	 * @param nY amount of rows
	 * @param j the column of the pixel (0 <= j < nX)
	 * @param i the row of the pixel (0 <= i < nY)
	 * @return the ray through the pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point P;
		try {
			P=location.add(vto.scale(VPdistance)); 
		}catch(IllegalArgumentException e) {
			P=location;
		}
		double y=-(i-(nY-1d)/2)*VPheight;
		double x=-(j-(nX-1d)/2)*VPwidth;
		if(!isZero(x))P=P.add(vright.scale(x));
		if(!isZero(y))P=P.add(vup.scale(y));
		try {
			return new Ray(location,P.subtract(location));
		}catch(IllegalArgumentException e) {
			return new Ray(location,vto);
		}
	}
}
