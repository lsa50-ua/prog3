package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
	private int size;
	private Map<Coordinate, Fighter> board;
	
	public Board(int size) {
		this.size = size;
		board = new HashMap<Coordinate, Fighter>();
	}
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = board.get(c);
		if(f != null) {
			f = new Fighter(f);			
		}
		return f;		
	}
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		Coordinate c = f.getPosition();
		boolean eliminado = false;
		
		if(c != null) {
			if(board.get(c) != null && board.get(c).equals(f)) {
				eliminado = true;
				board.remove(c);
			}
		}
		return eliminado;
	}
	
}
