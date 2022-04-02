/**
 * 
 */
package renderer;

import java.util.List;

import primitives.*;
import scene.Scene;

/**
 * basic ray tracer
 * @author ori frankel and yair sprecher
 */
public class RayTracerBasic extends RayTracerBase{
	/**
	 * constructor, sets scene
	 * @param scene the scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}
	@Override
	public Color traceRay(Ray ray) {
		List<Point> list=scene.geometries.findIntersections(ray);
		if(list==null||list.size()==0)return scene.background;
		return calcColor(ray.findClosestPoint(list));
	}
	private Color calcColor(Point p) {
		return scene.ambientLight.getIntensity();
	}
}
