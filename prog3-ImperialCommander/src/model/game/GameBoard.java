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
