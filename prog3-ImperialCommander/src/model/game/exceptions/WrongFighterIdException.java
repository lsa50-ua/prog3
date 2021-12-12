package model.game.exceptions;
/**
 * Excepcion que avisa de id incorrecto
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	/**
	 * atributo id
	 */
	private int id;
	/**
	 * Constructor de la clase
	 * @param id identificador
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	/**
	 * Mensaje de error
	 * @return devuelve una string con el error en cuestion
	 */
	public String getMessage() {
		return "ERROR: El siguiente id no esta disponible: " + id;
	}
}
