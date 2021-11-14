package model.exceptions;
import model.Fighter;
/**
 * Excepcion en la que el luchador ya esta destruido
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception {
	/**
	 * atributo fighter
	 */
	private Fighter f;
	/**
	 * Constructor de la clase
	 * @param f fighter
	 */
	public FighterIsDestroyedException(Fighter f) {
		super();
		this.f = f;
	}
	/**
	 * Mensaje de Error
	 * @return devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: hay un caza destruido " + f;
	}
}
