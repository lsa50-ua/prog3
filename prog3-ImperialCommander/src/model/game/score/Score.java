package model.game.score;

import model.Side;
/**
 * clase abstracta Score que encapsula el concepto de la puntuación de una partida
 * @author Luis Simón Albarrán 48804855M
 *
 */

/**
 * clase abstracta Score que encapsula el concepto de la puntuación de una partida
 * @author Luis Simón Albarrán 48804855M
 *
 * @param <T> tipo
 */
public abstract class Score<T> implements Comparable<Score<T>> {
	/**
	 * puntuacion
	 */
	protected int score;
	/**
	 * bando
	 */
	private Side side;
	/**
	 * Constructor clase Score
	 * @param side bando
	 */
	public Score(Side side) {
		this.side = side;
		score = 0;
	}
	/**
	 * getter de la puntuacion
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * representación en forma de cadena de caracteres del score
	 * @return string del score
	 */
	public String toString() {
		return "Player " + side + ": " + score;
	}
	/**
	 * compara dos puntuaciones por su valor.
	 * @param other sirve para compararlo con el implicito
	 * @return devuelve un entero
	 */
	public int compareTo(Score<T> other) {
		int r = 0;
		
		if (score > other.score) {
			r = -1;
		}
		else {
			if(score < other.score) {
				r = 1;
			}
			else {
				r = side.compareTo(other.side);
			}
		}
		return r;
	}
	
	/**
	 * abs
	 * @param sc tipo de score
	 */
	public abstract void score(T sc);
	
	
}
