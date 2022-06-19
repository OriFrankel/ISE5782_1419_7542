package primitives;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for black board
 * 
 * @author Ori Frankel and Yair Sprecher
 *
 */
public class BlackBoard {
	private int squareSize = 1;
	private Point center;
	private Vector vUp;
	private Vector vRight;
	private double size = 100;
	private static Vector firstTry = new Vector(0, 0, 1);
	private static Vector secondTry = new Vector(0, 1, 0);

	/**
	 * constructor for BlackBoard
	 * 
	 * @param v vector to the board
	 */
	public BlackBoard(Vector v, Point center) {
		try {
			vUp = v.crossProduct(firstTry).normalize();
		} catch (IllegalArgumentException e) {
			vUp = v.crossProduct(secondTry).normalize();
		}
		vRight = vUp.crossProduct(v).normalize();
		this.center = center;
	}

	/**
	 * setter for amountOfRays
	 * 
	 * @param amountOfRays the amount of rays
	 * @return the object itself
	 */
	public BlackBoard setAmountOfRays(int amountOfRays) {
		this.squareSize = (int) Math.sqrt(amountOfRays) / 2;
		if (this.squareSize < 1)
			this.squareSize = 1;
		return this;
	}

	/**
	 * setter for size
	 * 
	 * @param size the size
	 * @return the object itself
	 */
	public BlackBoard setSize(double size) {
		this.size = size;
		return this;
	}

	/**
	 * calculate set of points
	 * 
	 * @return the object
	 */
	public List<Point> calculatePoints() {
		List<Point> list = new LinkedList<Point>();
		Vector v1 = vUp.scale(size/squareSize), v2 = vRight.scale(size/squareSize);
		for (int i = -squareSize; squareSize >= i; ++i) {
			Point cur = center.subtract(v1.scale(squareSize));
			if (i != 0)
				cur = cur.add(v2.scale(i));
			for (int j = -squareSize; squareSize >= j; ++j) {
				list.add(cur);
				cur = cur.add(v1);
			}
		}
		return list;
	}

}