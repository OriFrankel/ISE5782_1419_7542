package lighting;

import java.util.LinkedList;
import java.util.List;

import primitives.BlackBoard;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for light source that is in a point
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class PointLight extends Light implements LightSource {
	/**
	 * the position of the light
	 */
	protected final Point position;
	/**
	 * the quadratic coefficient
	 */
	protected double kQ = 0;
	/**
	 * the linear coefficient
	 */
	protected double kL = 0;
	/**
	 * the constant coefficient
	 */
	protected double kC = 1;
	private double space = 1;

	@Override
	public Color getIntensity(Point p) {
		double dist = position.distance(p);
		return intensity.reduce(kC + kL * dist + kQ * dist * dist);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}

	/**
	 * setter for kQ
	 * 
	 * @param kQ the quadratic coefficient
	 * @return the resulting light source
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	/**
	 * setter for kL
	 * 
	 * @param kL the linear coefficient
	 * @return the resulting light source
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * setter for kC
	 * 
	 * @param kC the constant coefficient
	 * @return the resulting light source
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * constructor, gets color for light and position
	 * 
	 * @param color    the color
	 * @param position the position
	 */
	public PointLight(Color color, Point position) {
		super(color);
		this.position = position;
	}

	@Override
	public double getDistance(Point p) {
		return position.distance(p);
	}

	@Override
	public List<Vector> getVecs(Point p, int amountOfRays) {
		if (amountOfRays == 1)
			return List.of(getL(p).scale(-1));
		List<Point> list = new BlackBoard(position.subtract(p), position).setSpace(space).setAmountOfRays(amountOfRays)
				.calculatePoints();
		List<Vector> res = new LinkedList<Vector>();
		for (Point p1 : list)
			res.add(p1.subtract(p));
		return res;
	}
}
