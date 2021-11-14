package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo XWing
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class XWing extends Fighter  {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private XWing(XWing f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new XWing(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'X';
	}

}
