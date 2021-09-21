package model;

public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	public Coordinate(Coordinate c) {
		x = c.x;
		y = c.y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Coordinate add(Coordinate c) {
		
		Coordinate newC = null;
		
		newC = new Coordinate(x + c.x, y + c.y);
		
		return newC;
		
	}
	public Coordinate  add(int x, int y) {
		Coordinate newC = null;
		newC = new Coordinate(this.x + x, this.y + y);
		return newC;
	}
	public String to_string () {
		
		return "[" + x + "," + y + "]";
	}
	
}
