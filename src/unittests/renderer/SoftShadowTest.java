package unittests.renderer;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.green;

import org.junit.jupiter.api.Test;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

class SoftShadowTest {

	private Intersectable sphere = new Sphere(60d, new Point(0, 0, -200)) //
			.setEmission(new Color(BLUE)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
	private Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);

	private Scene scene = new Scene("Test scene").setAmountOfRays(121);
	private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(200, 200).setVPDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene));

	/**
	 * Helper function for the tests in this module
	 */
	void sphereTriangleHelper(String pictName, Triangle triangle, Point spotLocation) {
		scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
						.setKl(1E-5).setKq(1.5E-7));
		camera.setImageWriter(new ImageWriter(pictName, 400, 400)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void sphereTriangleInitial() {
		sphereTriangleHelper("shadowSphereTriangleInitialSoftShadows", //
				new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
				new Point(-100, -100, 200));
	}
	/**
	 * Our picture with soft shadows
	 */
	@Test
	public void fourObjectsTest() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(BLACK), 0.15));

		scene.geometries.add( //
				new Plane(new Point(0, 0, -200), new Vector(0, 0, 1)).setEmission(new Color(green))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.5)),
				new Sphere(new Point(60, 50, 50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.3)),
				new Triangle(new Point(65, 55, 55), new Point(45, 65, 45), new Point(75, 65, 35))//
						.setEmission(new Color(RED))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(600, 50, 500), new Vector(-10, 0, -10)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("ourTestSoftShadows", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}
}
