
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.MissingResourceException;

/**
 * the camera for making pictures
 * 
 * @author ori frankel and yair sprecher
 */
public class Camera {
	private Point location;
	private Vector vTo, vUp, vRight;
	private double viewPlaneWidth, viewPlaneHeight, viewPlaneDistance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * Constructor for camera,gets location and direction vectors
	 * 
	 * @param loc location of the camera
	 * @param vTo vector from the camera to the view plane
	 * @param vUp vector to the upper direction (must be orthogonal to vTo)
	 */
	public Camera(Point loc, Vector vTo, Vector vUp) {
		if (!isZero(vTo.dotProduct(vUp)))
			throw new IllegalArgumentException("vectors must be orthogonal");
		location = loc;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		this.vRight = this.vTo.crossProduct(this.vUp);
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
	 * setter for rayTracerBase
	 * 
	 * @return the resulting camera
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * setter for ray Tracer
	 * 
	 * @return the resulting camera
	 */
	public Camera setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
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
		p = location.add(vTo.scale(viewPlaneDistance));

		double y = ((nY - 1.0) / 2 - i) * viewPlaneHeight / nY;
		double x = (j - (nX - 1.0) / 2) * viewPlaneWidth / nX;
		if (!isZero(x))
			p = p.add(vRight.scale(x));
		if (!isZero(y))
			p = p.add(vUp.scale(y));
		try {
			return new Ray(location, p.subtract(location));
		} catch (IllegalArgumentException e) {
			return new Ray(location, vTo);
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

	/**
	 * render the image
	 */
	public Camera renderImage() {
		if (location == null || vTo == null || vUp == null || vRight == null || isZero(viewPlaneDistance)
				|| imageWriter == null || rayTracer == null)
			throw new MissingResourceException("", "", "");
		for (int i = 0; imageWriter.getNx() > i; ++i)
			for (int j = 0; imageWriter.getNy() > j; ++j)
				imageWriter.writePixel(i, j, castRay(i, j));
		return this;
	}

	/**
	 * print grid to the image
	 * 
	 * @param interval size of the grid in pixles
	 * @param color    color of the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("", "", "");
		for (int i = 0; imageWriter.getNx() > i; ++i)
			for (int j = 0; imageWriter.getNy() > j; ++j)
				if (i % (interval ) == 0 || j % (interval) == 0)
					imageWriter.writePixel(i, j, color);
	}

	/**
	 * create png image
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("", "", "");
		imageWriter.writeToImage();
	}

	private Color castRay(int i, int j) {
		return rayTracer.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), i, j));
	}
}
