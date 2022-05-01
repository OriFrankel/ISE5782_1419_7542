package lighting;

import primitives.*;

/**
 * interface for a light source
 * 
 * @author ori frankel and yair sprecher
 *
 */
public interface LightSource {
	/**
	 * get the intensity on a point
	 * 
	 * @param p the point
	 * @return the intensity
	 */
	public Color getIntensity(Point p);

	/**
	 * get the direction on a point
	 * 
	 * @param p the point
	 * @return the direction
	 */
	public Vector getL(Point p);
	
	/**
	 * TODO
	 * @param point some Point
	 * @return the distance from the source to a point
	 */
	public double getDistance(Point point);
}
