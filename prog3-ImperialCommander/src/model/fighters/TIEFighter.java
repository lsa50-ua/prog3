package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEFighter extends Fighter  {
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}
	private TIEFighter(TIEFighter f) {
		super(f);
	}
	public Fighter copy() {
		return new TIEFighter(this);
	}
	
	@Override
	public char getSymbol() {
		return 'f';
	}
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		return damage;
	}
}
