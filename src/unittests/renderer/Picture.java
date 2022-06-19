package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Intersectable;
import geometries.Polygon;
import geometries.Sphere;
import lighting.PointLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

class Picture {
	private Intersectable sphere = new Sphere(60d, new Point(0, 200, 0)) //
			.setEmission(new Color(WHITE)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.5));
	private PointLight pL = new PointLight(new Color(YELLOW), new Point(0, 0, 1)).setKl(0.00001).setKq(0.000002).setSize(100);
	private Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
	private Scene scene = new Scene("Test scene");
	private Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
			.setVPSize(500, 500).setVPDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene).setAmountOfRays(121));
	@Test
	void test() {
		scene.geometries.add(new Polygon(new Point(-50, 50, 50), new Point(50,50,50), new Point(50, -50, 50), new Point(-50, -50, 50))
				.setMaterial(trMaterial).setEmission(new Color(GREEN)));
		scene.geometries.add(sphere);
		scene.lights.add(pL);
		camera.setImageWriter(new ImageWriter("picture", 400, 400)) //
		.renderImage() //
		.writeToImage();
	}

}
