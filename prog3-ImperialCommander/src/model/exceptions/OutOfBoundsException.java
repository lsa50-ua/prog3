package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	private Coordinate position;
	
	public OutOfBoundsException(Coordinate pos) {
		super();
		position = pos;
	}
	public String getMessage() {
		return "ERROR: La coordenada esta fuera de los limites del tablero " + position;
	}

}
