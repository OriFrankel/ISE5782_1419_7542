
/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for light source that is in a point
 * @author ori frankel and yair sprecher 
 *
 */
public class PointLight extends Light implements LightSource {
	protected Point position;
	protected double kQ=0,kL=0,kC=1;
	
	@Override
	public Color getIntensity(Point p) {
		double dist=position.distance(p);
		return getIntensity().reduce(kC+kL*dist+kQ*dist*dist);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
	/**
	 * setter for kQ
	 * @param kQ the quadratic coefficient
	 * @return the resulting light source
	 */
	public PointLight setKq(double kQ) {
		this.kQ=kQ;
		return this;
	}
	/**
	 * setter for kL
	 * @param kL the linear coefficient
	 * @return the resulting light source
	 */
	public PointLight setKl(double kL) {
		this.kL=kL;
		return this;
	}
	/**
	 * setter for kC
	 * @param kC the constant coefficient
	 * @return the resulting light source
	 */
	public PointLight setKc(double kC) {
		this.kC=kC;
		return this;
	}
	/**
	 * constructor, gets color for light and position
	 * @param color the color
	 * @param position the position
	 */
	public PointLight(Color color,Point position) {
		super(color);
		this.position=position;
	}
}
