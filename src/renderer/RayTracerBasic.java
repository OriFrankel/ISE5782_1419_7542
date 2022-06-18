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
	private int amountOfRays = 1;

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

	/**
	 * recursively calculate the color at point
	 * 
	 * @param p     the point
	 * @param ray   the ray from the camera
	 * @param level level of recurtion
	 * @param k     the coefficient of the color, to stop the recurtion when it is
	 *              small
	 * @return the color
	 */
	private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
		Color color = p.geometry.getEmission().add(calcLocalEffects(p, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
	}

	/**
	 * calculate the local effects - the color without reflection/refraction
	 * 
	 * @param intersection the point to calculate the color in
	 * @param ray          ray from camera
	 * @return the color
	 */
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
				Double3 tra = transparency(intersection, lightSource, n, nv);
				if (!tra.equals(Double3.ZERO)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(lightIntensity.scale(calcDiffusive(material.kD, nl)
							.add(calcSpecular(material.kS, l, n, nl, v, material.nShininess)).product(tra)));
				}

			}
		}
		return color;
	}

	/**
	 * recursively calculate the global effects - reflection/refraction
	 * 
	 * @param gp    point to calculate the color
	 * @param ray   ray from camera
	 * @param level level of recursion
	 * @param k     the coefficient of the color, to stop the recursion when it is
	 *              small
	 * @return the color
	 */
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

	/**
	 * calculate the specular component of the color
	 * 
	 * @param kS         the specular coefficient
	 * @param l          vector from light source
	 * @param n          normal of the surface
	 * @param nl         n.dotProduct(l)
	 * @param v          vector from camera
	 * @param nShininess the shininess of the body
	 * @return the specular component of the color
	 */
	private Double3 calcSpecular(Double3 kS, Vector l, Vector n, Double nl, Vector v, int nShininess) {
		Vector r = l.subtract(n.scale(nl * 2));
		double d = -r.dotProduct(v);
		return alignZero(d) <= 0 ? Double3.ZERO : kS.scale(Math.pow(d, nShininess));
	}

	/**
	 * calculate the Diffusive component of the color
	 * 
	 * @param kD the Diffusive coefficient
	 * @param nl the dotProduct of normal of the surface and vector from light
	 *           source
	 * @return the Diffusive component of the color
	 */
	private Double3 calcDiffusive(Double3 kD, double nl) {
		return (kD.scale(nl > 0 ? nl : -nl));
	}

	private Double3 transparency(GeoPoint gp, LightSource lS, Vector n, double nv) {

		List<Vector> vecs = lS.getVecs(gp.point, amountOfRays);
		Double3 res = Double3.ZERO;
		for (Vector v1 : vecs)
			res = res.add(transparency(gp, lS, v1, n, nv));
		res = res.reduce(amountOfRays);
		return res;
	}

	/**
	 * calculate the transparency, how much light from the light source comes to the
	 * point
	 * 
	 * @param gp             the point
	 * @param lS             the light source
	 * @param lightDirection vector to calculate transparency
	 * @param n              normal at the point
	 * @param nv             dotproduct of n and the vector from the camera
	 * @return the transparency
	 */
	private Double3 transparency(GeoPoint gp, LightSource lS, Vector lightDirection, Vector n, double nv) {
		// Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
		// Point point = gp.point.add(epsVector);
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		Double3 ktr = Double3.ONE;
		if (intersections == null)
			return ktr;
		double dist = lS.getDistance(gp.point);
		for (GeoPoint intersection : intersections) {
			if (alignZero(gp.point.distance(intersection.point) - dist) < 0)
				ktr = ktr.product(intersection.geometry.getMaterial().kT);
		}
		return ktr;
	}

	/**
	 * build reflection Ray from a point
	 * 
	 * @param gp the point
	 * @param n  normal to the surface at the point
	 * @param v  vector from camera to the point
	 * @return the reflection ray
	 */
	private Ray reflectionRayConstruction(GeoPoint gp, Vector n, Vector v) {
		double nv = n.dotProduct(v);
		// Point point = gp.point.add(n.scale(nv < 0 ? DELTA : -DELTA));
		return new Ray(gp.point, v.subtract(n.scale(2 * nv)), n);
	}

	/**
	 * build refraction Ray from a point
	 * 
	 * @param gp the point
	 * @param n  normal to the surface at the point
	 * @param v  vector from camera to the point
	 * @return the refraction ray
	 */
	private Ray refractionRayConstruction(GeoPoint gp, Vector n, Vector v) {
		// double nv=n.dotProduct(v);
		// Point point = gp.point.add(n.scale(nv > 0 ? DELTA : -DELTA));
		return new Ray(gp.point, v, n);
	}

	/**
	 * calculate color at point, call the recursive function
	 * 
	 * @param gp  the point
	 * @param ray ray from camera
	 * @return the color
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE));
	}

	/**
	 * set amount of rays
	 * 
	 * @param amountOfRays
	 */
	public RayTracerBasic setAmountOfRays(int amountOfRays) {
		this.amountOfRays = amountOfRays;
		return this;
	}
}
