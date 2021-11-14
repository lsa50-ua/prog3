package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo AWing
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class AWing extends Fighter {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private AWing(AWing f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new AWing(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'A';
	}
	/**
	 * Modifica el daño depende de sus counters o si counterea a otros cazas.
	 * @param n numero aleatorio
	 * @param enemy enemigo
	 * @return devuelve el daño
	 */
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		if(enemy instanceof TIEBomber) {
			damage *= 2;
		}
		return damage;
	}
	
}
