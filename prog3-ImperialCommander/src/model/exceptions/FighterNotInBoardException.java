package model.exceptions;

import model.Fighter;
/**
 * Excepcion en la que el luchador no esta en el board
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception{
	/**
	 * atributo fighter
	 */
	private Fighter f;
	/**
	 * Constructor de la clase
	 * @param f fighter
	 */
	public FighterNotInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	/**
	 * Mensaje de Error
	 * @param devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: El fighter no esta en el tablero " + f;
	}
}
