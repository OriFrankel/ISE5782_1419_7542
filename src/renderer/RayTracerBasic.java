/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.*;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

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
		if (list == null)
			return scene.background;
		return calcColor(ray.findClosestGeoPoint(list), ray);
	}

	private Color calcColor(GeoPoint p, Ray ray) {
		return scene.ambientLight.getIntensity().add(p.geometry.getEmission()).add(calcLocalEffects(p, ray));
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(material.kD, nl, lightIntensity))
						.add(calcSpecular(material.kS, l, n, nl, v, material.nShininess, lightIntensity));
			}
		}
		return color;
	}

	private Color calcSpecular(Double3 kS, Vector l, Vector n, Double nl, Vector v, int nShininess, Color intensity) {
		Vector r = l.subtract(n.scale(nl * 2));
		double d = -r.dotProduct(v);
		return alignZero(d) <= 0 ? Color.BLACK : intensity.scale(kS.scale(Math.pow(d, nShininess)));
	}

	private Color calcDiffusive(Double3 kD, double nl, Color intensity) {
		return intensity.scale(kD.scale(nl > 0 ? nl : -nl));
	}
}
