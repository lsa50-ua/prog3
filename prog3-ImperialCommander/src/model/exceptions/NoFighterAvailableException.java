package model.exceptions;

/**
 * Excepcion en la que el luchador no esta disponible
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	/**
	 * atributo tipo
	 */
	private String type;
	/**
	 * Constructor de la clase
	 * @param t tipo
	 */
	public NoFighterAvailableException(String t) {
		super();
		type = t;
	}
	/**
	 * Mensaje de Error
	 * @return devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: No se ha encontrado ningun caza disponible del tipo indicado " + type;
	}

}
