package model;

public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	public Coordinate(final Coordinate c) {
		x = c.x;
		y = c.y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
