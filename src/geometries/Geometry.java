package geometries;

import primitives.*;

/**
 * abstract class for geometrical shape
 * 
 * @author ori frankel and yair sprecher
 *
 */
public abstract class Geometry extends Intersectable {
	protected Color emission=Color.BLACK;
	/**
	 * normal on a point in the geometry
	 * 
	 * @param p the point
	 * @return the normal
	 */
	public abstract Vector getNormal(Point p);
	/**
	 * getter for emission
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}
	/**
	 * setter for emission
	 * @param emission the emission
	 * @return the resulting object
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
}