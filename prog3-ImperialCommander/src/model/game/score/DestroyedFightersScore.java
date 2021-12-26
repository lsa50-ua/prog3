package model.game.score;

import model.Fighter;
import model.Side;
/**
 * Esta clase representa una puntuación basada en Fighter y suma el valor de los diferentes cazas destruidos por la nave del jugador.
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class DestroyedFightersScore extends Score<Fighter>{
	/**
	 * Constructor 
	 * @param side bando
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	/**
	 * Este método incrementa el atributo score con el valor del caza recibido como argumento (si no es null), usando el método Fighter.getValue() descrito anteriormente.
	 * @param f fighter pasado
	 */
	@Override
	public void score(Fighter f) {
		int aumento;
		if(f != null) {
			aumento = f.getValue();
			score += aumento;
		}
		
	}

}
