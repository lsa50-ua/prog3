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
	public Side getSide() {
		return side;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}
	public ArrayList<Fighter> getFleetTest(){
		return fleet;
	}
	public void addFighters(String fd) {
		int cantidad;
		String trozos[];
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
	public void updateResults(int r) {
		if(r == 1) {
			wins ++;
		}
		else {
			if(r == -1) {
				losses ++;
			}
		}
	}
	public Fighter getFirstAvailableFighter(String type) {
		Fighter firstAvailable = null;
		if(type == "") {
			for(Fighter f: fleet) {
				if(f.isDestroyed() == false) {
					firstAvailable = f;
					break;
				}
			}
		}
		else {
			for(Fighter f: fleet) {
				if(f.isDestroyed() == false && f.getType() == type) {
					firstAvailable = f;
					break;
				}
			}
		}
		
		return firstAvailable;
	}
	public void purgeFleet() {
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()== true) {
				fleet.remove(i);
				i--;
			}
		}
	}
	public String showFleet() {
		String list = "";
		for(Fighter f: fleet) {
			list = list + f.toString();
			if(f.isDestroyed() == true) {
				list = list + " (X)";
			}
			list = list + "\n";
			
		}
		return list;
	}
	public String myFleet() {
		int contador;
		StringBuilder sb = new StringBuilder();
		ArrayList<String> tipos = new ArrayList<>();
		ArrayList<Integer> cuantos = new ArrayList<>();
		sb.append("");
		for(int i = 0; i < fleet.size(); i++) {
			if(tipos.contains(fleet.get(i).getType()) == false && fleet.get(i).isDestroyed() == false) {
				tipos.add(fleet.get(i).getType());
				contador = 0;
				for(Fighter f: fleet) {
					if(f.getType() == fleet.get(i).getType() && f.isDestroyed() == false) {
						contador++;
					}
				}
				cuantos.add(contador);
			}	
		}
		for(int i = 0; i < tipos.size(); i++) {
			sb.append(Integer.toString(cuantos.get(i)) + "/" + tipos.get(i));
			if(i != tipos.size() - 1) {
				sb.append(":");
			}
		}
		return sb.toString();
	}
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "] " + this.myFleet();  
	}
}
