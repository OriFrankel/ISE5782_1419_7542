/**
 * 
 */
package lighting;

import primitives.*;

/**
 * class for constant light
 * 
 * @author ori frankel and yair sprecher
 */
public class AmbientLight extends Light {

	/**
	 * constructor for AmbientLight, gets color and Discount factor
	 * 
	 * @param color the color
	 * @param k     Discount factor
	 */
	public AmbientLight(Color color, Double3 k) {
		super( color.scale(k));
	}

	/**
	 * constructor, initializes intensity to black
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}

}
