package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
/**
 * clase para gestionar una nave en el juego
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class GameShip extends Ship{
	/**
	 * Constructor que lo único que debe hacer es llamar al constructor de la clase padre
	 * @param name nombre
	 * @param side bando
	 */
	public GameShip(String name, Side side) {
		super(name, side);
	}
	/**
	 * responde si esta la flota destruida
	 * @return true o false
	 */
	public boolean isFleetDestroyed() {
		boolean destruidos = true;
		for(Fighter f: fleet) {
			if(f.isDestroyed() == false) {
				destruidos = false;
			}
		}
		return destruidos;
	}
	/**
	 * Método privado que busca en la flota de la nave un caza cuyo identificador coincida con el argumento id.
	 * @param id identificador
	 * @return fighter buscado
	 * @throws WrongFighterIdException si el id no coincide con ninguno se lanza
	 */
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
	/**
	 * Devuelve una lista con los identificadores de los cazas (no destruidos) de la flota de la nave
	 * @param where en fucion de lo que pases te pasa una lista de ids o otra
	 * @return lista de ids buscada
	 */
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
	/**
	 * Obtiene el caza indicado por el argumento id y lo lanza al tablero b en la coordenada c
	 * @param id identificador
	 * @param c coordenada
	 * @param b tablero
	 * @throws FighterAlreadyInBoardException el fighter ya esta en el tablero
	 * @throws OutOfBoundsException fuera de los limites del tablero
	 * @throws WrongFighterIdException si el id no coincide con ninguno se lanza
	 */
	public void launch(int id, Coordinate c, Board b) throws FighterAlreadyInBoardException, OutOfBoundsException, WrongFighterIdException{
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		b.launch(c, getFighter(id));
	}
	/**
	 * Obtiene el caza indicado por id y lo pone a patrullar en el tablero b
	 * @param id identificador
	 * @param b tablero
	 * @throws FighterNotInBoardException fighter no esta en el board
	 * @throws WrongFighterIdException si el id no coincide con ninguno se lanza
	 */
	public void patrol(int id, Board b) throws FighterNotInBoardException, WrongFighterIdException{
		Objects.requireNonNull(b);
		b.patrol(getFighter(id));
	}
	/**
	 * Obtiene el caza indicado por id, lo quita del tablero  y se mejorará el caza
	 * @param id identificador
	 * @param qty cantidad
	 * @param b tablero
	 * @throws WrongFighterIdException si el id no coincide con ninguno se lanza
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(b);
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
