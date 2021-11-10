package model.exceptions;


@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	private String type;
	
	public NoFighterAvailableException(String t) {
		super();
		type = t;
	}
	public String getMessage() {
		return "ERROR: No se ha encontrado ningun caza disponible del tipo indicado " + type;
	}

}
