/**
 * 
 */
package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * class for light source that have a direction and is in a point
 * @author ori frankel and yair sprecher 
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;
	@Override
	public Color getIntensity(Point p) {
		double d=alignZero(getL(p).dotProduct(direction));
		if(d>0) {
			return super.getIntensity(p).scale(d);
		}
		return Color.BLACK;
	}
	/**
	 * constructor, gets intensity of the light,position  and direction
	 * @param intensity the intensity
	 * @param position the position
	 * @param direction the direction
	 * 
	 */
	public SpotLight(Color intensity,Point position, Vector direction) {
		super(intensity,position);
		this.direction=direction.normalize();
		
	}
}
