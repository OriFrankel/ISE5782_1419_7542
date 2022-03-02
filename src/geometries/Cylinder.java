package geometries;
import primitives.Ray;
/**
 * finite cylinder
 * @author ori frankel and yair sprecher
 *
 */
public class Cylinder extends Tube {
	double height;
	/**
	 * 
	 * @param ray the axis
	 * @param radius the radius
	 * @param height the height
	 */
	public Cylinder(Ray ray,double radius,double height) {
		super(ray,radius);
		this.height=height;
	}
	/**
	 * getter for height
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	@Override
	public String toString() {
		return super.toString()+" height: "+((Double)height).toString();
	}
}
