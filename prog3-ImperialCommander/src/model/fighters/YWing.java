package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo YWing
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class YWing extends Fighter  {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private YWing(YWing f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new YWing(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'Y';
	}
	/**
	 * Modifica el daño depende de sus counters o si counterea a otros cazas.
	 * @param n numero aleatorio
	 * @param enemy enemigo
	 * @return devuelve el daño
	 */
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		if(enemy instanceof TIEFighter || enemy instanceof TIEInterceptor) {
			damage /= 3;
		}
		else {
			if(enemy instanceof TIEBomber) {
				damage /= 2;
			}
		}
		return damage;
	}
}
