package model.game;

import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.*;

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
		boolean continuar = true;
		int r, seleccion, x, y;
		List<Integer> ids = null;
		r = RandomNumber.newRandomNumber(100);
		if(r == 99) {
			continuar = false;
		}
		else {
			if(r <= 24) {
				ids = ship.getFightersId("board");
			}
			else {
				if(r <= 84) {
					ids = ship.getFightersId("ship");
				}
				else {
					ids = ship.getFightersId("");
				}
			}
		}
		if(ids.isEmpty() == false) {
			try {
				seleccion = RandomNumber.newRandomNumber(ids.size());
				if(r <= 24) {
					ship.patrol(seleccion, board);
				}
				else {
					if(r <= 84) {
						x = RandomNumber.newRandomNumber(board.getSize());
						y = RandomNumber.newRandomNumber(board.getSize());
						ship.launch(seleccion, new Coordinate(x, y), board);
					}
					else {
						ship.improveFighter(seleccion, r, board);	
					}
				}
			}	
			catch(OutOfBoundsException | 
					FighterNotInBoardException | 
					FighterAlreadyInBoardException | 
					WrongFighterIdException e) {
				throw new RuntimeException(e);
			}
			
		}
		return continuar;
	}
}
