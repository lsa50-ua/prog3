package model.game;

import java.io.BufferedReader;
import java.io.IOException;

import model.Side;
import model.exceptions.FighterNotInBoardException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerFile implements IPlayer {
	private BufferedReader br;
	private GameShip ship;
	private GameBoard board;
	
	public PlayerFile(Side side, BufferedReader br) {
		this.br = br;
		ship = new GameShip("PlayerFile " + side + " Ship", side);
		board = null;
	}
	
	@Override
	public void setBoard(GameBoard gb) {
		board = gb;
		
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
		}catch(IOException e) {throw new RuntimeException(e);}
		if(linea == "exit") {
			continuar = false;
		}
		else {
			trozos = linea.split(" ");
			if(trozos[0] == "improve") {
				
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
			}
			if(trozos[0] == "patrol") {
				if(trozos.length == 2) {
					id = Integer.parseInt(trozos[1]);
					try {
						ship.patrol(id, board);
					}catch(FighterNotInBoardException | WrongFighterIdException e) {System.out.println(e);}
				}
				else {
					System.out.println("ERROR: linea leida incorrecta");
				}
			}
			if(trozos[0] == "launch") {
				if(trozos.length == 2) {
					x = Integer.parseInt(trozos[1]);
					y = Integer.parseInt(trozos[2]);
				}
				if(trozos.length == 3) {
					
				}
				else {
					System.out.println("ERROR: linea leida incorrecta");
				}
			}
		}
		
		return continuar;
		
	}
	
}
