package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter  {
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	private YWing(YWing f) {
		super(f);
	}
	public Fighter copy() {
		return new YWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'Y';
	}
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
