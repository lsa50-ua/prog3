package model.game;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private final static int BOARD_SIZE = 10;
	private IPlayer rebel;
	private IPlayer imperial;
	private GameBoard board;
	
	public Game(IPlayer imperial, IPlayer rebel) {
		this.imperial = imperial;
		this.rebel = rebel;
		try {
			board = new GameBoard(BOARD_SIZE);
			imperial.setBoard(board);
			rebel.setBoard(board);
		}catch(InvalidSizeException e) {throw new RuntimeException(e);}
	}
	public GameBoard getGameBoard() {
		return board;
	}
	public Side play() {
		Side winner = null;
		boolean continuar = true;
		imperial.initFighters();
		rebel.initFighters();
		
		do {
			System.out.println("BEFORE IMPERIAL\n" + board + "\n");
			System.out.println(imperial.showShip() + "\n" + rebel.showShip());
			System.out.print("IMPERIAL(" + board.numFighters(Side.IMPERIAL) + "): ");
			continuar = imperial.nextPlay();
			if(continuar == true) {
				System.out.println("AFTER IMPERIAL, BEFORE REBEL\n" + board + "\n");
				System.out.println(imperial.showShip() + "\n" + rebel.showShip());
				if(imperial.isFleetDestroyed() == false && rebel.isFleetDestroyed() == false) {
					System.out.print("REBEL(" + board.numFighters(Side.REBEL) + "): ");
					continuar = rebel.nextPlay();
					if(continuar == true) {
						System.out.println("AFTER REBEL\n" + board + "\n");
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
		if(continuar == true) {
			imperial.purgeFleet();
			rebel.purgeFleet();
			if(rebel.isFleetDestroyed()) {
				winner = Side.IMPERIAL;
			}
			else {
				winner = Side.REBEL;
			}
		}
		return winner;
	}
}
