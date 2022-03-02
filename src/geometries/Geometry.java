package geometries;
/**
 * interface for geometrical shape
 * @author ori frankel and yair sprecher
 *
 */
import primitives.Vector;
import primitives.Point;
public interface Geometry {
	public Vector getNormal(Point p); 
}
