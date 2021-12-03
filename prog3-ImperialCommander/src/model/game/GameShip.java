package model.game;

import model.Fighter;
import model.Ship;
import model.Side;
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
	
}
