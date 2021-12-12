package model;

import java.util.Objects;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;
/**
 * Clase que representa la fabrica de cazas
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class FighterFactory {
	/**
	 * Metodo para crear cazas
	 * @param type Indica el tipo
	 * @param mother Indica la nave
	 * @return devuelve el caza del tipo especificado y lo mete en la nave especificada.
	 */
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		Fighter f = null;
		switch(type) {
			case "AWing":
				f = new AWing(mother);
				break;
			case "XWing":
				f = new XWing(mother);
				break;
			case "YWing":
				f = new YWing(mother);
				break;
			case "TIEFighter":
				f = new TIEFighter(mother);
				break;
			case "TIEBomber":
				f = new TIEBomber(mother);
				break;
			case "TIEInterceptor":
				f = new TIEInterceptor(mother);
				break;
		}
		return f;
	}
}
