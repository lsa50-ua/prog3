package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo TIEInterceptor
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class TIEInterceptor extends Fighter  {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new TIEInterceptor(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'i';
	}
	/**
	 * Modifica el daño depende de sus counters o si counterea a otros cazas.
	 * @param n numero aleatorio
	 * @param enemy enemigo
	 * @return devuelve el daño
	 */
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		if(enemy instanceof YWing) {
			damage *= 2;
		}
		else {
			if(enemy instanceof AWing) {
				damage /= 2;
			}
		}
		return damage;
	}
}
