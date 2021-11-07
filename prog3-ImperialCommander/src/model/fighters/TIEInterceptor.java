package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEInterceptor extends Fighter  {
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	public Fighter copy() {
		return new TIEInterceptor(this);
	}
	
	@Override
	public char getSymbol() {
		return 'i';
	}
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
