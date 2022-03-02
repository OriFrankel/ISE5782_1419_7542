import geometries.Tube;
import primitives.Ray;

public class Cylinder extends Tube {
	double height;
	public Cylinder(Ray ray,double radius,double height) {
		super(ray,radius);
		this.height=height;
	}
	public double getHeight() {
		return height;
	}
	@Override
	public String toString() {
		return super.toString()+" height: "+((Double)height).toString();
	}
}
