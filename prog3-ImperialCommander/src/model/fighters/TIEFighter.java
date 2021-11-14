package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo TIEFighter
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class TIEFighter extends Fighter  {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private TIEFighter(TIEFighter f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new TIEFighter(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'f';
	}

}
