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
	public String toString () {
		
		return "[" + x + "," + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	x
}
