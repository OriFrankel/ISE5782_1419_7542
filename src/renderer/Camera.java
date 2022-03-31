
package renderer;

import primitives.*;
import static primitives.Util.*;

/**
 * the camera for making pictures
 * 
 * @author ori frankel and yair sprecher
 */
public class Camera {
	private Point location;
	private Vector vto, vup, vright;
	double viewPlaneWidth, viewPlaneHeight, viewPlaneDistance;

	/**
	 * Constructor for camera,gets location and direction vectors
	 * 
	 * @param loc location of the camera
	 * @param vto vector from the camera to the view plane
	 * @param vup vector to the upper direction (must be orthogonal to vto)
	 */
	public Camera(Point loc, Vector vto, Vector vup) {
		if (!isZero(vto.dotProduct(vup)))
			throw new IllegalArgumentException("vectors must be orthogonal");
		location = loc;
		this.vto = vto.normalize();
		this.vup = vup.normalize();
		this.vright = this.vto.crossProduct(this.vup);
	}

	/**
	 * setter for width of view plane,height of view plane
	 * 
	 * @param width  the width
	 * @param height the height
	 * @return the resulting camera
	 */
	public Camera setVPSize(double width, double height) {
		viewPlaneWidth = width;
		viewPlaneHeight = height;
		return this;
	}

	/**
	 * setter for distance of view plane
	 * 
	 * @return the resulting camera
	 */
	public Camera setVPDistance(double distance) {
		if (isZero(distance))
			throw new IllegalArgumentException();
		viewPlaneDistance = distance;
		return this;
	}

	/**
	 * construct ray from the camera through a pixel, given its place
	 * 
	 * @param nX amount of columns
	 * @param nY amount of rows
	 * @param j  the column of the pixel (0 <= j < nX)
	 * @param i  the row of the pixel (0 <= i < nY)
	 * @return the ray through the pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point p;
		p = location.add(vto.scale(viewPlaneDistance));

		double y = ((nY - 1.0) / 2 - i) * viewPlaneHeight / nY;
		double x = (j - (nX - 1.0) / 2) * viewPlaneWidth / nX;
		if (!isZero(x))
			p = p.add(vright.scale(x));
		if (!isZero(y))
			p = p.add(vup.scale(y));
		try {
			return new Ray(location, p.subtract(location));
		} catch (IllegalArgumentException e) {
			return new Ray(location, vto);
		}
	}

	/**
	 * setter for location
	 * 
	 * @param location location of the camera
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
}
