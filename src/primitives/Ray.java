package primitives;
/**
 * ray in the space
 * @author Ori Frankel, Yair Sprecher
 *
 */
public class Ray {
	private final Point p0;
	private final Vector dir;
	/**
	 * constructor, for Ray, gets start point and direction
	 * @param p the start point
	 * @param v the direction
	 */
	public Ray(Point p, Vector v) {
		p0 = p;
		dir = v.normalize();
	}
	/**
	 * getter for Dir
	 * @return Dir
	 */
	public Vector getDir() {
		return dir;
	}
	/**
	 * getter for p0
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}
	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Ray))
			return false;
		Ray ray = (Ray)other;
		return ray.p0.equals(p0) && ray.dir.equals(dir);
	}
	@Override
	public String toString() {
		return p0.toString() + dir.toString();
	}
}