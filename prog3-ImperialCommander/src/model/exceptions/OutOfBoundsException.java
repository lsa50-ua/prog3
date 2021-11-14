package model.exceptions;

import model.Coordinate;
/**
 * Excepcion en la que el luchador esta fuera de los limites del tablero
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	/**
	 * atributo posicion
	 */
	private Coordinate position;
	/**
	 * Constructor de la clase
	 * @param f fighter
	 */
	public OutOfBoundsException(Coordinate pos) {
		super();
		position = pos;
	}
	/**
	 * Mensaje de Error
	 * @param devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: La coordenada esta fuera de los limites del tablero " + position;
	}

}
