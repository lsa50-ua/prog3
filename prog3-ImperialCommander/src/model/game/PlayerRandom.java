package model.game;

import model.Side;

public class PlayerRandom {
	private int numFighters;
	private GameShip ship;
	private GameBoard board;
	public PlayerRandom(Side side, int numFighters) {
		this.numFighters = numFighters;
		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		board = null;
	}
	
}
