/**
 * 
 */
package lighting;

import java.util.List;

import primitives.*;

/**
 * class for light source that have a direction
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private final Vector direction;

	@Override
	public Color getIntensity(Point p) {
		return intensity;
	}

	@Override
	public Vector getL(Point p) {
		return direction;
	}

	/**
	 * constructor, gets intensity of the light and direction
	 * 
	 * @param intensity the intensity
	 * @param direction the direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}
	@Override
	public double getDistance(Point p) {
		return Double.POSITIVE_INFINITY;
	}
	@Override
	public List<Vector> getVecs(Point p, int amountOfRays) {
		return List.of(getL(p).scale(-1));
	}
}
