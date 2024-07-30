//Pun Bahadur Chhetri
//Kunj Bhavsar
//CS351-Project4
//Mazes-
package Visualization;

public class Point {

	private int x;
	private int y;

	private Point parent;

	private int f;

	public Point(int x, int y, Point parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Output X Coordinate
	public int getX() {
		return this.x;
	}

	// Output Y Coordinate
	public int getY() {
		return this.y;
	}

	// Output F Cost
	public int getF() {
		return this.f;
	}

	// Output Parent Point
	public Point getParent() {
		return this.parent;
	}

	// Set F Cost
	public void setF(int f) {
		this.f = f;
	}
}
