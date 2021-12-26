package model.game.score;

import model.Side;
/**
 * Esta clase representa un score basado en Integer y simplemente lleva la cuenta de las victorias obtenidas por la nave del jugador.
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class WinsScore extends Score<Integer>{
	/**
	 * Constructor
	 * @param side bando
	 */
	public WinsScore(Side side) {
		super(side);
	}
	/**
	 * Aumenta el score si el parametro pasado es 1
	 * @param w depende de este parametro aumenta o no el score
	 */
	public void score(Integer w) {
		if(w != null && w == 1) {
			score ++;
		}
	}
	
}
