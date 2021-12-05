package model.game;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShip extends Ship{
	public GameShip(String name, Side side) {
		super(name, side);
	}
	public boolean isFleetDestroyed() {
		boolean destruidos = true;
		for(Fighter f: fleet) {
			if(f.isDestroyed() == false) {
				destruidos = false;
			}
		}
		return destruidos;
	}
	private Fighter getFighter(int id) throws WrongFighterIdException {
		Fighter buscado = null;
		for(Fighter f: fleet) {
			if(f.getId() == id && f.isDestroyed() == false) {
				buscado = f;
				break;
			}
		}
		if(buscado == null) {
			throw new WrongFighterIdException(id);
		}
		return buscado;
	}
	public List<Integer> getFightersId(String where){
		List<Integer> ids = new ArrayList<>();
		
		if(where == "board") {
			for(Fighter f: fleet) {
				if(f.isDestroyed() == false && f.getPosition() != null) {
					ids.add(f.getId());
				}
			}
		}
		else {
			if(where == "ship") {
				for(Fighter f: fleet) {
					if(f.isDestroyed() == false && f.getPosition() == null) {
						ids.add(f.getId());
					}
				}
			}
			else {
				for(Fighter f: fleet) {
					if(f.isDestroyed() == false) {
						ids.add(f.getId());
					}
				}
			}
		}
		return ids;
	}
	public void launch(int id, Coordinate c, Board b) throws FighterAlreadyInBoardException, OutOfBoundsException, WrongFighterIdException{
		b.launch(c, getFighter(id));
	}
	public void patrol(int id, Board b) throws FighterNotInBoardException, WrongFighterIdException{
		b.patrol(getFighter(id));
	}
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Fighter f = getFighter(id);
		try {
			b.removeFighter(f);
		}catch(FighterNotInBoardException ex) {}
		if(qty % 2 == 0) {
			f.addAttack(qty/2);
			f.addShield(qty/2);
		}
		else {
			f.addAttack(qty/2);
			f.addShield((qty/2) + 1);
		}
	}
	
}
