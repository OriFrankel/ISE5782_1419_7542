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
	private static final double DELTA = 0.01;

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
				if (unshaded(intersection, lightSource, l, n, nv)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(material.kD, nl, lightIntensity))
							.add(calcSpecular(material.kS, l, n, nl, v, material.nShininess, lightIntensity));
				}
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

	private boolean unshaded(GeoPoint gp, LightSource lS, Vector l, Vector n, double nv) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
		Point point = gp.point.add(epsVector);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return true;
		double dist = lS.getDistance(point);
		for (GeoPoint intersection:intersections) {
			if (alignZero(point.distance(intersection.point) - dist) < 0)
				return false;
		}
		return true;
	}
}
