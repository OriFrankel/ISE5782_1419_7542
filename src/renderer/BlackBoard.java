package renderer;


import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Vector;

/**
 * Class for black board
 * 
 * @author Ori Frankel and Yair Sprecher
 *
 */
public class BlackBoard {
	private int amountOfRays = 1;
	private Vector vUp;
	private Vector vRight;
	private double space;
	private static Vector firstTry = new Vector(0, 0, 1);
	private static Vector secondTry = new Vector(0, 1, 0);

	/**
	 * constructor for BlackBoard
	 * 
	 * @param v vector to the board
	 */
	public BlackBoard(Vector v) {
		try {
			vUp = v.crossProduct(firstTry).normalize();
		} catch (IllegalArgumentException e) {
			vUp = v.crossProduct(secondTry).normalize();
		}
		vRight = vUp.crossProduct(v).normalize();
	}

	/**
	 * setter for amountOfRays
	 * 
	 * @param amountOfRays the amount of rays
	 * @return the object itself
	 */
	public BlackBoard setAmountOfRays(int amountOfRays) {
		this.amountOfRays = (int)Math.sqrt(amountOfRays)/2;
		return this;
	}

	/**
	 * setter for space
	 * 
	 * @param space the space
	 * @return the object itself
	 */
	public BlackBoard setSpace(double space) {
		this.space = space;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public List<Point> calculatePoints() {
		List<Point> list = new LinkedList<Point>();
		return null;
	}
}