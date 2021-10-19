package model;

import java.util.ArrayList;

public class Ship {
	private String name;
	private int wins;
	private int losses;
	private ArrayList<Fighter> fleet;
	private Side side;
	
	public Ship(String name, Side side){
		this.name = name;
		this.side = side;
		wins = 0;
		losses = 0;
		fleet = new ArrayList<Fighter>();
	}
	public ArrayList<Fighter> getFleetTest(){
		return fleet;
	}
	public void addFighters(String fd) {
		int cantidad;
		String trozos[], tipo;
		if (fd.contains(":")) {
			String listaCazas[] = fd.split(":");
			for(String caza : listaCazas) {
				trozos = caza.split("/");
				cantidad = Integer.parseInt(trozos[0]);
				for(int i = 0; i < cantidad; i++) {
					fleet.add(new Fighter(trozos[1], this));
				}
				
			}
		}
		else {
			if(fd.contains("/")) {
				trozos = fd.split("/");
				cantidad = Integer.parseInt(trozos[0]);
				for(int i = 0; i < cantidad; i++) {
					fleet.add(new Fighter(trozos[1], this));
				}
			}
		}
		
	}
	
	public Side getSide() {
		return side;
	}
}
