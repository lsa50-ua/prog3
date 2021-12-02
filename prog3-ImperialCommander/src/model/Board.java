package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
/**
 * Esta clase representa el tablero cuadrado en el que se desarrollará el juego
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class Board {
	/**
	 * tamaño del tablero 
	 */
	private int size;
	/**
	 * mapa para almacenar los cazas en posiciones del tablero
	 */
	protected Map<Coordinate, Fighter> board;
	/**
	 * Constructor que inicializa los datos del tablero
	 * @param size indica el tamaño con el que se inicializa el tablero
	 * @throws InvalidSizeException invalidSize
	 */
	public Board(int size) throws InvalidSizeException{
		if(size < 5) {
			throw new InvalidSizeException(size);
		}
		this.size = size;
		board = new HashMap<Coordinate, Fighter>();
	}
	/**
	 * Getter que devuelve el contenido del tablero en la posición indicada
	 * @param c posicion indicada
	 * @return contenido del tablero 
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = board.get(c);
		if(f != null) {
			f = f.copy();		
		}
		return f;		
	}
	/**
	 * Elimina el fighter que se indica
	 * @param f fighter indicado 
	 * @throws FighterNotInBoardException fighter no esta en el tablero
	 * @return devuelve true or false en funcion de si lo consigue eliminar
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		Coordinate c = f.getPosition();
		boolean eliminado = false;
		
		if(c == null) {
			throw new FighterNotInBoardException(f);
		}
		if(board.get(c) == null) {
			throw new FighterNotInBoardException(f);
		}
		if(board.get(c).equals(f) == false) {
			throw new FighterNotInBoardException(f);
		}
		eliminado = true;
		board.remove(c);
		f.setPosition(null);
		
		return eliminado;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Comprueba que la coordenada indicada este dentro del tablero
	 * @param c coordenada indicada
	 * @return Devuelve true si la coordenada está dentro del tablero, y false en otro caso
	 */
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
	/**
	 * 
	 * @param c Coordenada de la que hay que devolver la posiciones vecinas
	 * @throws OutOfBoundsException fuera de los limites
	 * @return Devuelve un TreeSet con las posiciones vecinas a la coordenada c que estén dentro del tablero
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		Set<Coordinate> neighbors = new TreeSet<Coordinate>();
		Set<Coordinate> in = new TreeSet<Coordinate>();
		if(inside(c) == false) {
			throw new OutOfBoundsException(c);
		}
		neighbors = c.getNeighborhood();
		
		for(Coordinate nueva: neighbors) {
			if(inside(nueva) == true) {
				in.add(nueva);
			}
		}
		return in;
	}
	/**
	 * Intenta colocar un caza en una posición del tablero, si hay otro enemigo luchan entre si
	 * @throws FighterAlreadyInBoardException ya esta en el tablero
	 * @throws OutOfBoundsException fuera de los limites
	 * @param c coordenada a la que es lanzado el caza
	 * @param f	fighter que es lanzado
	 * @return devuelve el resultado de la batalla o 0 en cualquier otro caso
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter enemy;
		int batalla = 0;
		if(f.getPosition() != null) {
			throw new FighterAlreadyInBoardException(f);
		}
		if(board.containsValue(f)) {
			throw new FighterAlreadyInBoardException(f);
		}
		if(inside(c) != true) {
			throw new OutOfBoundsException(c);
		}
		if(board.containsKey(c) == true) {
			enemy = board.get(c);
			if(f.getSide() != enemy.getSide()) {
				try {
					batalla = f.fight(enemy);
				} catch(FighterIsDestroyedException destroyed) {
					throw new RuntimeException(destroyed);
				}
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
		

		return batalla;
	}
	/**
	 * El caza (si está en el tablero) recorre su vecindad y pelea si es necesario
	 * @param f caza indicado
	 * @throws FighterNotInBoardException no esta en el tablero
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		Coordinate pos;
		int batalla;
		Fighter enemy;
		pos = f.getPosition();
		Set<Coordinate> neighbors = null;
		
		if(pos == null) {
			throw new FighterNotInBoardException(f);
		}
		try {
			neighbors = getNeighborhood(pos);
		} catch(OutOfBoundsException out) {
			throw new RuntimeException(out);
		}
		if(board.get(pos) == null) {
			throw new FighterNotInBoardException(f);
		}
		if(board.get(pos).equals(f) == false) {
			throw new FighterNotInBoardException(f);
		}
		if(pos != null) {
			Fighter fBoard = board.get(pos);
			if(fBoard.equals(f)) {
				
				for(Coordinate c: neighbors) {
					if(f.getPosition() == null) {
						break;
					}
					enemy = board.get(c);
					if(enemy != null && f.getSide() != enemy.getSide()) {
						try {
							batalla = f.fight(enemy);
						} catch (FighterIsDestroyedException destroyed) {
							throw new RuntimeException(destroyed);
						}
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
