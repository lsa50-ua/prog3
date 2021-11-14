package model.exceptions;
/**
 * Excepcion en la que el tamaño del tablero es excedido
 * @author Luis Simón Albarrán 48804855M
 *
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception {
	/**
	 * atributo size
	 */
	private int size;
	/**
	 * Constructor de la clase
	 * @param size tamaño
	 */
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	/**
	 * Mensaje de Error
	 * @return devuelve el mensaje.
	 */
	public String getMessage() {
		return "ERROR: El tamaño es menor que 5, tamaño: " + size;
	}
}
