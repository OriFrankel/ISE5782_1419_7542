package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
	private Point position;
	private double kC, kL, kQ;
	
	@Override
	public Color getIntensity(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}
}