/**
 * 
 */
package scene;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import geometries.*;
import lighting.*;

/**
 * all of the components of a scene
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Scene {
	/**
	 * name of the scene
	 */
	public String name;
	/**
	 * background color of the scene
	 */
	public Color background = Color.BLACK;
	/**
	 * ambient light of the objects in the scene
	 */
	public AmbientLight ambientLight = new AmbientLight();
	/**
	 * the shapes in the scene
	 */
	public Geometries geometries = new Geometries();
	/**
	 * the light sources in the scene
	 */
	public List<LightSource> lights = new LinkedList<>();
	public int amountOfRays=1;

	/**
	 * constructor, sets defaults
	 * 
	 * @param name name of the scene
	 */
	public Scene(String name) {
		this.name = name;

	}

	/**
	 * set the background color
	 * 
	 * @param color the new color
	 * @return the resulting scene
	 */
	public Scene setBackground(Color color) {
		background = color;
		return this;
	}

	/**
	 * set the ambient Light
	 * 
	 * @param light the new light
	 * @return the resulting scene
	 */
	public Scene setAmbientLight(AmbientLight light) {
		ambientLight = light;
		return this;
	}

	/**
	 * set the geometries list
	 * 
	 * @param geometries the new geometries
	 * @return the resulting scene
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

	/**
	 * set the lights list
	 * 
	 * @param lights the new lights
	 * @return the resulting scene
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
	/**
	 * set amount of rays
	 * @param amountOfRays
	 */
	public Scene setAmountOfRays(int amountOfRays) {
		this.amountOfRays = amountOfRays;
		return this;
	}
}
