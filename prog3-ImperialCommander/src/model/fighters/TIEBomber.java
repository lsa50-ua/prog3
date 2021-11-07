package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEBomber extends Fighter  {
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	public Fighter copy() {
		return new TIEBomber(this);
	}
	
	@Override
	public char getSymbol() {
		return 'b';
	}
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
