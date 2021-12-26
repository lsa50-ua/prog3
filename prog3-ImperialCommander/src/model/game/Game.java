package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;
/**
 * Esta clase gestiona una partida entre dos jugadores, uno imperial y otro rebelde
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class Game {
	/**
	 * constante tamaño del tablero
	 */
	private final static int BOARD_SIZE = 10;
	/**
	 * jugador rebelde
	 */
	private IPlayer rebel;
	/**
	 * jugador imperial
	 */
	private IPlayer imperial;
	/**
	 * tablero del juego
	 */
	private GameBoard board;
	/**
	 * Constructor que guarda en sus atributos los jugadores que se le pasan como argumentos,
	 * crea un tablero de tamaño BOARD_SIZE y se lo asigna a los jugadores
	 * @param imperial jugador imperial
	 * @param rebel jugador rebel
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		this.imperial = imperial;
		this.rebel = rebel;
		try {
			board = new GameBoard(BOARD_SIZE);
			imperial.setBoard(board);
			rebel.setBoard(board);
		}catch(InvalidSizeException e) {throw new RuntimeException(e);}
	}
	/**
	 * Devuelve el tablero
	 * @return tablero
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	/**
	 * metodo auxiliar para el metodo play
	 */
	private void showRankings() {
		Ranking<WinsScore> rw;
		Ranking<DestroyedFightersScore> rd;
		
		rw = new Ranking<>();
		rd = new Ranking<>();
		
		rw.addScore(imperial.getWinsScore());
		rw.addScore(rebel.getWinsScore());
		
		rd.addScore(imperial.getDestroyedFightersScore());
		rd.addScore(rebel.getDestroyedFightersScore());
		
		System.out.println("RANKING WINS: " + rw);
		System.out.println("RANKING DESTROYED: " + rd);
	}
	/**
	 * metodo para jugar 
	 * @return el bando ganador
	 */
	public Side play() {
		Side winner = null;
		boolean continuar = true;
		imperial.initFighters();
		rebel.initFighters();
		
		do {
			showRankings();
			System.out.println("BEFORE IMPERIAL\n" + board);
			System.out.println();
			System.out.println(imperial.showShip() + "\n" + rebel.showShip());
			System.out.print("IMPERIAL(" + board.numFighters(Side.IMPERIAL) + "): ");
			continuar = imperial.nextPlay();
			if(continuar == true) {
				System.out.println("AFTER IMPERIAL, BEFORE REBEL\n" + board);
				System.out.println();
				System.out.println(imperial.showShip() + "\n" + rebel.showShip());
				if(imperial.isFleetDestroyed() == false && rebel.isFleetDestroyed() == false) {
					System.out.print("REBEL(" + board.numFighters(Side.REBEL) + "): ");
					continuar = rebel.nextPlay();
					if(continuar == true) {
						System.out.println("AFTER REBEL\n" + board);
						System.out.println();
						System.out.println(imperial.showShip() + "\n" + rebel.showShip());
						imperial.purgeFleet();
						rebel.purgeFleet();
					}
					else {
						winner = Side.IMPERIAL;
					}
				}
			}
			else {
				winner = Side.REBEL;
			}
			
		}while(continuar == true && imperial.isFleetDestroyed() == false && rebel.isFleetDestroyed() == false);
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		showRankings();
		if(continuar == true) {

			if(imperial.isFleetDestroyed()) {
				winner = Side.REBEL;
			}
			else {
				winner = Side.IMPERIAL;
			}
		}
		return winner;
	}
}
