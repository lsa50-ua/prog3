package model.game;

import model.RandomNumber;
import model.Side;

public class PlayerRandom implements IPlayer{
	private int numFighters;
	private GameShip ship;
	private GameBoard board;
	public PlayerRandom(Side side, int numFighters) {
		this.numFighters = numFighters;
		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		board = null;
	}
	@Override
	public void setBoard(GameBoard g) {
		board = g;
	}
	@Override
	public GameShip getGameShip() {
		return ship;
	}
	@Override
	public void initFighters() {
		String imperials[] = {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String rebels[] = {"XWing", "YWing", "AWing"};
		int randomNum;
		String cadena = "";
		
		if(ship.getSide() == Side.IMPERIAL) {
			for(int i = 0; i < imperials.length; i++) {
				randomNum = RandomNumber.newRandomNumber(numFighters);
				if(randomNum > 0) {
					cadena += randomNum + "/" + imperials[i];
					if(i != imperials.length - 1) {
						cadena += ":";
					}
				}
			}
		}
		else {
			for(int i = 0; i < rebels.length; i++) {
				randomNum = RandomNumber.newRandomNumber(numFighters);
				if(randomNum > 0) {
					cadena += randomNum + "/" + rebels[i];
					if(i != rebels.length - 1) {
						cadena += ":";
					}
				}
			}
		}
		if(cadena.isEmpty() == false) {
			ship.addFighters(cadena);
		}
	}
	@Override
	public boolean isFleetDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String showShip() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void purgeFleet() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean nextPlay() {
		// TODO Auto-generated method stub
		return false;
	}
}
