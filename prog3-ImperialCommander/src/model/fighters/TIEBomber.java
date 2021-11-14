package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Clase de herencia de los cazas del tipo TIEBomber
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class TIEBomber extends Fighter  {
	/**
	 * Constructor
	 * @param mother nave 
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	/**
	 * Constructor copia
	 * @param f fighter a copiar
	 */
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public Fighter copy() {
		return new TIEBomber(this);
	}
	/**
	 * Metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	@Override
	public char getSymbol() {
		return 'b';
	}
	/**
	 * Modifica el daño depende de sus counters o si counterea a otros cazas.
	 * @param n numero aleatorio
	 * @param enemy enemigo
	 * @return devuelve el daño
	 */
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		if(enemy instanceof YWing || enemy instanceof XWing) {
			damage /= 2;
		}
		else {
			if(enemy instanceof AWing) {
				damage /= 3;
			}
		}
		return damage;
	}
}
