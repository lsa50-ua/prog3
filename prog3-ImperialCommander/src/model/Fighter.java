package model;

import java.util.Objects;

import model.exceptions.FighterIsDestroyedException;

/**
 * Fighter va a representar a un luchador con diferentes atributos
 * @author Luis Simón Albarrán 48804855M
 *
 */
public abstract class Fighter {
	/**
	 * velocidad del caza
	 */
	private int velocity;
	/**
	 * ataque del caza
	 */
	private int attack;
	/**
	 * escudo del caza
	 */
	private int shield;
	/**
	 * identificador del caza
	 */
	private int id;
	/**
	 * posicion del caza
	 */
	private Coordinate position;
	/**
	 * Nave del caza
	 */
	private Ship motherShip;
	/**
	 * indica el id para el proximo caza que se cree
	 */
	private static int nextId = 1;
	/**
	 * Constructor que inicializa un caza, asignando un valor de 100 a la velocidad, 80 al ataque y 80 al escudo
	 * @param mother asigna este valor al motherShip del caza
	 */
	protected Fighter(Ship mother){
		Objects.requireNonNull(mother);
		velocity = 100;
		attack = 80;
		shield = 80;
		motherShip = mother;
		id = nextId;
		nextId ++;
		
		
	}
	/**
	 * Constructor de copia que devuelve un caza con los mismos valores que el caza recibido
	 * @param f caza recibido
	 */
	protected Fighter(Fighter f) {
		Objects.requireNonNull(f);
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		id = f.id;
		position = f.position;
		motherShip = f.motherShip;
	}
	/**
	 * 
	 * @return devuelve la velocidad del caza
	 */
	public int getVelocity() {
		return velocity;
	}
	/**
	 * Añade una cantidad de velocidad indicada en el parametro al caza
	 * @param velocity velocidad a añadir
	 */
	public void addVelocity(int velocity) {
		if(this.velocity + velocity >= 0) {
			this.velocity += velocity;
		}
		else {
			this.velocity = 0;
		}
	}
	/**
	 * 
	 * @return devuelve el valor del ataque del caza
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * Añade la cantidad indicada en el parametro al ataque
	 * @param attack cantidad a añadir
	 */
	public void addAttack(int attack) {
		if(this.attack + attack >= 0) {
			this.attack += attack;
		}
		else {
			this.attack = 0;
		}
	}
	/**
	 * 
	 * @return devuelve el escudo del caza
	 */
	public int getShield() {
		return shield;
	}
	/**
	 * Suma el parametro pasado al escudo
	 * @param shield cantidad a añadir
	 */
	public void addShield(int shield) {
		this.shield += shield;
	}
	/**
	 * 
	 * @return devuelve el tipo del caza
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	/**
	 * 
	 * @return devuelve el identificador del caza
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return devuelve la posicion en la que se encuentra el caza
	 */
	public Coordinate getPosition() {
		return position;
	}
	/**
	 * establece la posicion indicada en el parametro al caza
	 * @param pos Coordenada donde se va a situar
	 */
	public void setPosition(Coordinate pos) {
		position = pos;
	}
	/**
	 * 
	 * @return devuelve la nave del caza
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	/**
	 * Reinicia el atributo que indica el siguiente identificador
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	/**
	 * Dice si el caza esta destruido
	 * @return devuelve true si esta destruido y false en caso contrario
	 */
	public boolean isDestroyed() {
		boolean destroyed = false;
		if(shield <= 0) {
			destroyed = true;
		}
		return destroyed;
	}
	/**
	 * Devuelve el bando del caza
	 * @return devuelve el bando
	 */
	public Side getSide() {
		return motherShip.getSide();
	}
	/**
	 * Devuelve el daño infligido por el caza al caza enemigo
	 * @param n	numero aleatorio pasado
	 * @param enemy el argumento enemy no se utiliza
	 * @return daño infligido por el caza al caza enemigo
	 */
	public int getDamage(int n,Fighter enemy) {
		Objects.requireNonNull(enemy);
		int damage = (n*attack)/300;
		return damage;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + getType() + " " + id + " " + getSide() + " ");
		if(position == null) {
			sb.append("null");
		}
		else {
			sb.append(position);
		}
		sb.append(" {" + velocity + "," + attack + "," + shield + "})");
		return sb.toString();
	}
	/**
	 * método que simula la lucha entre dos cazas
	 * @throws FighterIsDestroyedException ya esta destruido
	 * @param enemy caza enemigo
	 * @return devuelve 1 si ha ganado el caza o -1 si ha ganado el enemigo
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException{
		Objects.requireNonNull(enemy);
		int pelea, n;
		if(enemy.isDestroyed()) {
			throw new FighterIsDestroyedException(enemy);
		}
		if(isDestroyed()) {
			throw new FighterIsDestroyedException(this);
		}	

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
	public int getValue() {
		int valor = velocity + attack;
		return valor;
	}
	/**
	 * Metodo copy
	 * @return devuelve un objeto de la subclase obtenido del constructor copia.
	 */
	public abstract Fighter copy();
	/**
	 * metodo que devuelve el simbolo
	 * @return devuelve el simbolo del tipo de nave
	 */
	public abstract char getSymbol();
	
}
