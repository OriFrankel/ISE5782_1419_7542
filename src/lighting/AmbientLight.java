/**
 * 
 */
package lighting;
import primitives.*;
/**
 * class for constant light
 * @author ori frankel and yair sprecher
 */
public class AmbientLight {
	final private Color intensity;
	/**
	 * constructor for AmbientLight, gets color and Discount factor
	 * @param color the color
	 * @param k Discount factor
	 */
	public AmbientLight(Color color,Double3 k) {
		intensity=color.reduce(k);
	}
	/**
	 * constructor, initializes intensity to black
	 */
	public AmbientLight() {
		intensity=Color.BLACK;
	}
	/**
	 * get the intensity of the light
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
