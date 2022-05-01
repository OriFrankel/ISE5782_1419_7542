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

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

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

	private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
		Color color = p.geometry.getEmission().add(calcLocalEffects(p, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
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
				color = color.add(lightIntensity.scale(
						calcDiffusive(material.kD, nl).add(calcSpecular(material.kS, l, n, nl, v, material.nShininess))
								.product(transparency(intersection, lightSource, l, n, nv))));

			}
		}
		return color;
	}

	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Double3 kr = gp.geometry.getMaterial().kR, kkr = kr.product(k);
		if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
			Ray reflectedRay = reflectionRayConstruction(gp, gp.geometry.getNormal(gp.point), ray.getDir());
			GeoPoint reflectedPoint = scene.geometries.findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		Double3 kt = gp.geometry.getMaterial().kT, kkt = kt.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
			Ray refractedRay = refractionRayConstruction(gp, gp.geometry.getNormal(gp.point), ray.getDir());
			GeoPoint refractedPoint = scene.geometries.findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}

	private Double3 calcSpecular(Double3 kS, Vector l, Vector n, Double nl, Vector v, int nShininess) {
		Vector r = l.subtract(n.scale(nl * 2));
		double d = -r.dotProduct(v);
		return alignZero(d) <= 0 ? Double3.ZERO : kS.scale(Math.pow(d, nShininess));
	}

	private Double3 calcDiffusive(Double3 kD, double nl) {
		return (kD.scale(nl > 0 ? nl : -nl));
	}

	private Double3 transparency(GeoPoint gp, LightSource lS, Vector l, Vector n, double nv) {
		Vector lightDirection = l.scale(-1); // from point to light source
		// Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
		// Point point = gp.point.add(epsVector);
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return Double3.ONE;
		Double3 ktr = Double3.ONE;
		double dist = lS.getDistance(gp.point);
		for (GeoPoint intersection : intersections) {
			if (alignZero(gp.point.distance(intersection.point) - dist) < 0)
				ktr = ktr.product(intersection.geometry.getMaterial().kT);
		}
		return ktr;
	}

	private Ray reflectionRayConstruction(GeoPoint gp, Vector n, Vector v) {
		double nv = n.dotProduct(v);
		// Point point = gp.point.add(n.scale(nv < 0 ? DELTA : -DELTA));
		return new Ray(gp.point, v.subtract(n.scale(2 * nv)), n);
	}

	private Ray refractionRayConstruction(GeoPoint gp, Vector n, Vector v) {
		// double nv=n.dotProduct(v);
		// Point point = gp.point.add(n.scale(nv > 0 ? DELTA : -DELTA));
		return new Ray(gp.point, v, n);
	}

	private Color calcColor(GeoPoint gp, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE));
	}
}
