package model.game;

import java.io.BufferedReader;
import java.io.IOException;

import model.Side;
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
		int qty, id;
		boolean continuar = true;
		try {
			linea = br.readLine();
		}catch(IOException e) {throw new RuntimeException(e);}
		if(linea == "exit") {
			continuar = false;
		}
		else if(linea.contains("improve")) {
			trozos = linea.split(" ");
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
		
		return continuar;
		
	}
	
}
