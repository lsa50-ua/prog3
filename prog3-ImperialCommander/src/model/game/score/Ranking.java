package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 * 
 * sistema de clasificación basado en las puntuaciones obtenidas por los diferentes jugadores
 * @author Luis Simón Albarrán 48804855M
 *
 * @param <ScoreType> tipo
 */
public class Ranking<ScoreType extends Score<?>> {
	/**
	 * conjunto ordenado
	 */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * constructor
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
		
	}
	/**
	 * Este método añade el objeto pasado como parámetro al conjunto scoreSet.
	 * @param st tipo de score
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	/**
	 * Devuelve el primer elemento del conjunto scoreSet.
	 * @return primer elemento del conjunto
	 */
	public ScoreType getWinner() {
		if(scoreSet.isEmpty()) {
			throw new RuntimeException();
		}
		else {
			return scoreSet.first();
		}
	}
	/**
	 * Un simple getter
	 * @return devuelve el conjunto
	 */
	public SortedSet<ScoreType> getSortedRanking() {
		return scoreSet;
	}
	/**
	 * Devuelve una cadena formada por las puntuaciones del ranking
	 * @return string de puntuaciones
	 */
	public String toString() {
		String s = "";
		for(ScoreType st : scoreSet) {
			s += " | " + st;
		}
		s += " | ";
		return s;
	}
}
