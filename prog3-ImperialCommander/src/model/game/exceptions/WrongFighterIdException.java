package model.game.exceptions;

@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	private int id;
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	public String getMessage() {
		return "ERROR: El siguiente id no esta disponible: " + id;
	}
}
