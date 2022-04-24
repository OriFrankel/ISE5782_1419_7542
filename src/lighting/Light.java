package lighting;

import primitives.*;
/**
 * class for light
 * 
 * @author ori frankel and yair sprecher
 */
abstract class Light {
	private Color intensity;
	protected Light(Color other) {
		intensity = other;
	}
	/**
	 * get the intensity of the light
	 * 
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}