/**
 * 
 */
package scene;

import primitives.*;

import geometries.*;
import lighting.*;

/**
 * all of the components of a scene
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Scene {
	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;

	/**
	 * constructor, sets defaults
	 * 
	 * @param name name of the scene
	 */
	public Scene(String name) {
		this.name = name;
		this.ambientLight = new AmbientLight();
		this.background = Color.BLACK;
		this.geometries = new Geometries();
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
}
