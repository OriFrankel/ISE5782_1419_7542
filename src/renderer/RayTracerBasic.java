/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

/**
 * basic ray tracer
 * 
 * @author ori frankel and yair sprecher
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * constructor, sets scene
	 * 
	 * @param scene the scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> list = scene.geometries.findGeoIntersections(ray);
		if (list == null || list.size() == 0)
			return scene.background;
		return calcColor(ray.findClosestGeoPoint(list));
	}

	private Color calcColor(GeoPoint p) {
		return scene.ambientLight.getIntensity().add(p.geometry.getEmission());
	}
}
