package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Interfaz que modela un jugador del juego, que contiene los métodos necesarios para jugar.
 * @author Luis Simón Albarrán 48804855M
 *
 */
public interface IPlayer {
	/**
	 * Asigna el tablero pasado como parámetro gb al atributo board del jugador
	 * @param gb gameboard
	 */
	void setBoard(GameBoard gb);
	/**
	 * Devuelve la nave del jugador (sin hacer copia defensiva), se usará para los tests
	 * @return nave del jugador
	 */
	GameShip getGameShip();
	/**
	 * Obtiene una cadena y llama al método addFighters de la nave del jugador. 
	 */
	void initFighters();
	/**
	 * Comprueba si la flota esta destruida
	 * @return true o false
	 */
	boolean isFleetDestroyed();
	/**
	 * Devuelve una cadena formada por la cadena que devuelve el método toString de la nave, 
	 * un cambio de línea y la cadena devuelta por el método showFleet.
	 * @return cadena 
	 */
	String showShip();
	/**
	 * Limpia los cazas destruidos de la nave
	 */
	void purgeFleet();
	/**
	 * Realiza una jugada
	 * @return si continua o no
	 */
	boolean nextPlay();
	/**
	 * getter score wins
	 * @return puntuacion de victorias
	 */
	WinsScore getWinsScore();
	/**
	 * getter cazas destruidos
	 * @return puntuacion de cazas destruidos
	 */
	DestroyedFightersScore getDestroyedFightersScore();
}
