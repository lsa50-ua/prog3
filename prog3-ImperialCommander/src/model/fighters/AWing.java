package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends Fighter {
	
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	private AWing(AWing f) {
		super(f);
	}
	public Fighter copy() {
		return new AWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'A';
	}
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		if(enemy instanceof TIEBomber) {
			damage *= 2;
		}
		return damage;
	}
	
}
