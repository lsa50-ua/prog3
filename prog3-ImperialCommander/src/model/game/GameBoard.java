package model.game;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

public class GameBoard extends Board {
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	public int numFighters(Side side) {
		Fighter f;
		Coordinate c;
		int cuantos = 0;
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				c = new Coordinate(i,j);
				f = board.get(c);
				if(f != null && f.getSide() == side) {
					cuantos++;
				}
			}
		}
		return cuantos;
	}
	public String toString() {
		
	}
	
}
