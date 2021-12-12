package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
/**
 * Esta clase permite leer los movimientos de un jugador desde un fichero
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class PlayerFile implements IPlayer {
	/**
	 * lectura buffereada
	 */
	private BufferedReader br;
	/**
	 * nave del jugador
	 */
	private GameShip ship;
	/**
	 * tablero del juego
	 */
	private GameBoard board;
	/**
	 * Constructor que inicializa la nave del jugador
	 * @param side bando
	 * @param br lectura buffereada
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		this.br = br;
		ship = new GameShip("PlayerFile " + side + " Ship", side);
		board = null;
	}
	
	@Override
	public void setBoard(GameBoard g) {
		Objects.requireNonNull(g);
		board = g;
		
	}

	@Override
	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void initFighters() {
		try {
			ship.addFighters(br.readLine());
		}catch(IOException e) {throw new RuntimeException(e);}
	}

	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}

	@Override
	public String showShip() {
		return ship + "\n" + ship.showFleet();
	}

	@Override
	public void purgeFleet() {
		ship.purgeFleet();
		
	}
	@Override
	public boolean nextPlay() {
		String linea = null;
		String [] trozos;
		int qty, id, x, y;
		boolean continuar = true;
		try {
			linea = br.readLine();
			trozos = linea.split(" ");
			
			switch(trozos[0]) {
				case "exit":
					continuar = false;
					break;
				case "launch":
					try {
						if(trozos.length == 3) {
							x = Integer.parseInt(trozos[1]);
							y = Integer.parseInt(trozos[2]);
							board.launch(new Coordinate(x, y), ship.getFirstAvailableFighter(""));
						}
						else {
							if(trozos.length == 4) {
								x = Integer.parseInt(trozos[1]);
								y = Integer.parseInt(trozos[2]);
								try {
									id = Integer.parseInt(trozos[3]);
									ship.launch(id, new Coordinate(x, y), board);
								}catch(NumberFormatException e) {
									board.launch(new Coordinate(x,y), ship.getFirstAvailableFighter(trozos[3]));
								}
							}
							else {
								System.out.println("ERROR: linea leida incorrecta");
							}
						}
					}catch(NoFighterAvailableException | FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException ex) {
						System.out.println(ex);
					}
					break;
					
				case "patrol":
					if(trozos.length == 2) {
						id = Integer.parseInt(trozos[1]);
						try {
							ship.patrol(id, board);
						}catch(FighterNotInBoardException | WrongFighterIdException e) {System.out.println(e);}
					}
					else {
						System.out.println("ERROR: linea leida incorrecta");
					}
					break;
					
				case "improve":
					if(trozos.length != 3) {
						System.out.println("ERROR: linea leida incorrecta");
					}
					else {
						id = Integer.parseInt(trozos[1]);
						qty = Integer.parseInt(trozos[2]);
						if(qty < 100) {
							try {
								ship.improveFighter(id, qty, board);
							}catch(WrongFighterIdException e) {System.out.println(e);}
							
						}
						else {
							System.out.println("ERROR: qty no es menor que 100");
						}
					}
					break;
					
				default:
					System.out.println("ERROR: " + trozos[0] + " no coincide");
			}
			
		}
		catch(IOException e) {throw new RuntimeException(e);}
		
		return continuar;
	}
}