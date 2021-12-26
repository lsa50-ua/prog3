package model;

import java.lang.reflect.Constructor;
import java.util.Objects;

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
		try {
			Class<?> c = Class.forName("model.fighters." + type);
			Constructor<?> m = c.getConstructor(Ship.class);
			return (Fighter) m.newInstance(mother);
			
		} catch(Exception e) { 
			return null;
		}
		/*
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
		*/
		
	}
	
}
