package model;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

public class FighterFactory {
	public static Fighter createFighter(String type, Ship mother) {
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
