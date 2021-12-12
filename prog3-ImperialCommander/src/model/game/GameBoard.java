package model.game;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;
/**
 * Subclase de Board que usaremos para el juego
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class GameBoard extends Board {
	/**
	 * Constructor que llama la padre
	 * @param size tamaño
	 * @throws InvalidSizeException excepcion de tamaño invalido
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	/**
	 * Devuelve el número de cazas que hay en el tablero del bando indicado por el argumento side
	 * @param side bando 
	 * @return el numero de cazas del bando indicado
	 */
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
	/**
	 * Cadena con una representación del tablero
	 * @return devuelve la cadena
	 */
	public String toString() {
		String tablero = "  ";
		Coordinate c;
		Fighter f;
		int i, fila, columna;
		for(i = 0; i < getSize(); i++) {
			tablero += i;
		}
		tablero += "\n  ";
		for(i = 0; i < getSize(); i++) {
			tablero += "-";
		}
		tablero += "\n";
		for(fila = 0; fila < getSize(); fila++) {
			tablero += fila + "|";
			for(columna = 0; columna < getSize(); columna++) {
				c = new Coordinate(columna, fila);
				f = board.get(c);
				if (f != null) {
					tablero += f.getSymbol();
				}
				else {
					tablero += " ";
				}
			}
			if(fila != getSize() - 1) {
				tablero += "\n";
			}
		}
		return tablero;
	}
	
}
