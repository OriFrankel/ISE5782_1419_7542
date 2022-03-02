package primitives;

public class Ray {
	final Point p0;
	final Vector dir;
	public Ray(Point p, Vector v) {
		p0 = p;
		dir = v.normalize();
	}
	public Vector getDir() {
		return dir;
	}
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