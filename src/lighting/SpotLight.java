/**
 * 
 */
package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * class for light source that have a direction and is in a point
 * 
 * @author Ori Frankel and Yair Sprecher
 *
 */
public class SpotLight extends PointLight {
	private final Vector direction;

	@Override
	public Color getIntensity(Point p) {
		double d = alignZero(getL(p).dotProduct(direction));
		return d > 0 ? super.getIntensity(p).scale(d) : Color.BLACK;
	}

	/**
	 * constructor, gets intensity of the light,position and direction
	 * 
	 * @param intensity the intensity
	 * @param position  the position
	 * @param direction the direction
	 * 
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();

	}
}