package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

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
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		boolean dentro = false;
		if(c != null) {
			if(c.getX() >= 0 && c.getX() <= (size - 1) && c.getY() >= 0 &&  c.getY() <= (size - 1)) {
				dentro = true;
			}
		}
		return dentro;
	}
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		Objects.requireNonNull(c);
		Set<Coordinate> neighbors = new TreeSet<Coordinate>();
		Set<Coordinate> in = new TreeSet<Coordinate>();		
		neighbors = c.getNeighborhood();
		
		for(Coordinate nueva: neighbors) {
			if(inside(nueva) == true) {
				in.add(nueva);
			}
		}
		return in;
	}
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter enemy;
		int batalla = 0;
		if(inside(c) == true) {
			if(board.containsKey(c) == true) {
				enemy = getFighter(c);
				if(f.getSide() != enemy.getSide()) {
					batalla = f.fight(enemy);
					if(batalla == 1) {
						f.getMotherShip().updateResults(batalla);
						enemy.getMotherShip().updateResults(-1);
						board.put(c, f);
						enemy.setPosition(null);
					}
					else {
						enemy.getMotherShip().updateResults(1);
						f.getMotherShip().updateResults(batalla);						
					}
				}
			}
			else {
				board.put(c, f);
				f.setPosition(c);
			}
		}
		return batalla;
	}
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		Coordinate pos;
		int batalla;
		Fighter enemy;
		pos = f.getPosition();
		
		if(pos != null) {
			Fighter fBoard = board.get(pos);
			if(fBoard.equals(f)) {
				Set<Coordinate> neighbors = getNeighborhood(pos);
				
				for(Coordinate c: neighbors) {
					if(f.getPosition() == null) {
						break;
					}
					enemy = board.get(c);
					if(enemy != null && f.getSide() != enemy.getSide()) {
						batalla = f.fight(enemy);
						if(batalla == 1) {
							f.getMotherShip().updateResults(batalla);
							enemy.getMotherShip().updateResults(-1);
							board.remove(enemy.getPosition());
							enemy.setPosition(null);
						}
						else {
							enemy.getMotherShip().updateResults(1);
							f.getMotherShip().updateResults(batalla);
							board.remove(f.getPosition());
							f.setPosition(null);
						}
					}
				}
			}
		}
	}
	
}
