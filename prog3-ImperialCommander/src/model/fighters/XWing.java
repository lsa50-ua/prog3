package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter  {
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	private XWing(XWing f) {
		super(f);
	}
	public Fighter copy() {
		return new XWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'X';
	}
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		return damage;
	}
}
