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
	private double space=1;
	private static Vector firstTry = new Vector(0, 0, 1);
	private static Vector secondTry = new Vector(0, 1, 0);
	private List<Point> list=null;

	/**
	 * constructor for BlackBoard
	 * 
	 * @param v vector to the board
	 */
	public BlackBoard(Vector v,Point center) {
		try {
			vUp = v.crossProduct(firstTry).normalize();
		} catch (IllegalArgumentException e) {
			vUp = v.crossProduct(secondTry).normalize();
		}
		vRight = vUp.crossProduct(v).normalize();
		this.center=center;
	}

	/**
	 * setter for amountOfRays
	 * 
	 * @param amountOfRays the amount of rays
	 * @return the object itself
	 */
	public BlackBoard setAmountOfRays(int amountOfRays) {
		this.squareSize = (int)Math.sqrt(amountOfRays)/2;
		if(this.squareSize<1)this.squareSize =1;
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
	 * calculate set of points
	 * @return the object
	 */
	public BlackBoard calculatePoints() {
		list = new LinkedList<Point>();
		Vector v1=vUp.scale(space),v2=vRight.scale(space);
		for(int i=-squareSize;squareSize>=i;++i) {
			Point cur=center.subtract(v1.scale(squareSize));
			if(i!=0)
				cur=cur.add(v2.scale(i));
			for(int j=-squareSize;squareSize>=j;++j) {
				list.add(cur);
				cur=cur.add(v1);
			}
		}
		return this;
	}
	/**
	 * get the list of points
	 * @return the list
	 */
	public List<Point> getList(){
		return list;
	}
}