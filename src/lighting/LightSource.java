package lighting;

import java.util.List;

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
	 * get vectors from the point to the light source
	 * @param p the point
	 * @param amountOfRays the amount of the rays
	 * @return a list of the vectors
	 */
	public List<Vector> getVecs(Point p, int amountOfRays);

	/**
	 * calculate the distance from a point to the lightSource
	 * 
	 * @param point some Point
	 * @return the distance from the source to a point
	 */
	public double getDistance(Point point);
}
