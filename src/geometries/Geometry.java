package geometries;

import primitives.*;

/**
 * abstract class for geometrical shape
 * 
 * @author ori frankel and yair sprecher
 *
 */
public abstract class Geometry extends Intersectable {
	protected Color emission = Color.BLACK;
	private Material material = new Material();
	private BoundingRegion bRegion = null;

	/**
	 * normal on a point in the geometry
	 * 
	 * @param p the point
	 * @return the normal
	 */
	public abstract Vector getNormal(Point p);

	protected abstract BoundingRegion getBoundingRegion();

	/**
	 * getter for emission
	 * 
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * setter for emission
	 * 
	 * @param emission the emission
	 * @return the resulting object
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * getter for the material
	 * 
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * setter for the material
	 * 
	 * @param material the material
	 * @return the resulting geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	/**
	 * 
	 * @return
	 */
	public BoundingRegion getBR() {
		if (bRegion == null)
			bRegion = getBoundingRegion();
		return bRegion;
	}
}