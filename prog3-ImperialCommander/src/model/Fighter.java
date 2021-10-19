package model;

public class Fighter {
	private String type;
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private Coordinate position;
	private Ship motherShip;
	private static int nextId = 1;
	
	Fighter(String type,Ship mother){
		velocity = 100;
		attack = 80;
		shield = 80;
		this.type = type;
		motherShip = mother;
		id = nextId;
		nextId ++;
		
		
	}
	public Fighter(Fighter f) {
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		type = f.type;
		id = f.id;
		position = f.position;
		motherShip = f.motherShip;
	}
	public int getVelocity() {
		return velocity;
	}
	public void addVelocity(int velocity) {
		this.velocity = velocity;
	}
	public int getAttack() {
		return attack;
	}
	public void addAttack(int attack) {
		this.attack = attack;
	}
	public int getShield() {
		return shield;
	}
	public void addShield(int shield) {
		this.shield = shield;
	}
	public String getType() {
		return type;
	}
	public int getId() {
		return id;
	}
	public Coordinate getPosition() {
		return position;
	}
	public void addPosition() {
		
	}
	public static void resetNextId() {
		nextId = 1;
	}
	public boolean isDestroyed() {
		boolean destroyed = false;
		if(shield <= 0) {
			destroyed = true;
		}
		return destroyed;
	}
	public Side getSide() {
		return motherShip.getSide();
	}
	public int getDamage(int n,Fighter enemy) {
		int damage = (n*attack)/300;
		return damage;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + type + " " + id + " " + getSide());
		if(position == null) {
			sb.append(" null");
		}
		else {
			sb.append(position);
		}
		sb.append(" {" + velocity + "," + attack + "," + shield + "})");
		return sb.toString();
	}
	public int fight(Fighter enemy) {
		int pelea, n;
		if(isDestroyed() || enemy.isDestroyed()) {
			pelea = 0;
		}
		else {
			while(isDestroyed() == false && enemy.isDestroyed() == false) {
				n = RandomNumber.newRandomNumber(100);
				if(((100 * velocity)/(velocity + enemy.velocity)) <= n) {
					enemy.shield = enemy.shield - getDamage(n, enemy);
				}
				else {
					shield = shield - enemy.getDamage(100 - n, this);
				}
			}
			if(enemy.isDestroyed() == true) {
				pelea = 1;
			}
			else {
				pelea = -1;
			}
		}
		return pelea;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Fighter)) {
			return false;
		}
		Fighter other = (Fighter) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
}