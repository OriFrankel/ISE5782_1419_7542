
/**
 * 
 */
package lighting;

import primitives.Color;

/**
 * class for light source
 * @author ori frankel and yair sprecher 
 *
 */
abstract class Light {
	private final Color intensity;
	/**
	 * constructor, sets intensity
	 * @param inten the intensity
	 */
	protected Light (Color inten) {
		intensity=inten;
	}
	/**
	 * get the intensity of the light
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
