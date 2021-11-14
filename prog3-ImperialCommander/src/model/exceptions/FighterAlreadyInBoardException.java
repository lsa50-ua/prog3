package model.exceptions;

import model.Fighter;
/**
 * Excepcion en la que el luchador ya esta en el tablero
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{
	/**
	 * atributo fighter
	 */
	private Fighter f;
	/**
	 * Constructor de la clase
	 * @param f fighter
	 */
	public FighterAlreadyInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	/**
	 * Mensaje de Error
	 * @param devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: Este caza ya tiene posición asignada " + f;
	}
}
