/**
 * 
 */
package renderer;

import primitives.*;
import scene.*;

/**
 * base ray tracer
 * 
 * @author ori frankel and yair sprecher
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * constructor, sets the scene
	 * 
	 * @param scene the scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * get the color for a ray
	 * 
	 * @param ray the ray
	 * @return the color
	 */
	public abstract Color traceRay(Ray ray);
}
