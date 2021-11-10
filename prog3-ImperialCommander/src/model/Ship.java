package model;

import java.util.ArrayList;
import java.util.List;
import model.exceptions.NoFighterAvailableException;
/**
 * Esta clase permite gestionar naves imperiales o rebeldes
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class Ship {
	/**
	 * nombre de la nave
	 */
	private String name;
	/**
	 * victorias obtenidas por los cazas de la nave
	 */
	private int wins;
	/**
	 * derrotas de los cazas de la nave
	 */
	private int losses;
	/**
	 * flota de cazas de la nave
	 */
	private ArrayList<Fighter> fleet;
	/**
	 * bando al que pertenece la nave
	 */
	private Side side;
	/**
	 * Constructor que inicializa los datos de la nave 
	 * @param name el name se inicializa con este parametro
	 * @param side el side se inicializa con este parametro
	 */
	public Ship(String name, Side side){
		this.name = name;
		this.side = side;
		wins = 0;
		losses = 0;
		fleet = new ArrayList<Fighter>();
	}
	/**
	 * 
	 * @return devuelve el bando al que pertenece
	 */
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
	/**
	 * 
	 * @return Devuelve el valor del atributo fleet
	 */
	public List<Fighter> getFleetTest(){
		return fleet;
	}
	/**
	 * Construye los cazas indicados en el string pasado como parametro
	 * @param fd string donde indica los cazas a construir
	 */
	public void addFighters(String fd) {
		int cantidad;
		String trozos[];
		if (fd.contains(":")) {
			String listaCazas[] = fd.split(":");
			for(String caza : listaCazas) {
				trozos = caza.split("/");
				cantidad = Integer.parseInt(trozos[0]);
				for(int i = 0; i < cantidad; i++) {
					fleet.add(FighterFactory.createFighter(trozos[1], this));
				}
				
			}
		}
		else {
			if(fd.contains("/")) {
				trozos = fd.split("/");
				cantidad = Integer.parseInt(trozos[0]);
				for(int i = 0; i < cantidad; i++) {
					fleet.add(FighterFactory.createFighter(trozos[1], this));
				}
			}
		}
		
	}
	/**
	 * actualiza los valores de wins o losses en función del valor del argumento r
	 * @param r depende de este se pondra win or loss
	 */
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
	/**
	 * Devuelve el primer caza (no destruido) de la flota del tipo indicado
	 * @param type tipo de caza que hay que elegir 
	 * @return el primer caza no destruido
	 */
	public Fighter getFirstAvailableFighter(String type) throws NoFighterAvailableException{
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
				if(f.isDestroyed() == false && type.equals(f.getType())) {
					firstAvailable = f;
					break;
				}
			}
		}
		if(firstAvailable == null) {
			throw new NoFighterAvailableException(type);
		}
		
		return firstAvailable;
	}
	/**
	 * Borra de la flota los cazas destruidos
	 */
	public void purgeFleet() {
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()== true) {
				fleet.remove(i);
				i--;
			}
		}
	}
	/**
	 * los cazas de la flota cada uno en una linea
	 * @return Devuelve una cadena en la que en cada línea se muestra un caza de la flota
	 */
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
	/**
	 * Se crea una cadena
	 * @return Devuelve una cadena con el mismo formato que admite el método addFighters con los cazas no destruidos de la flota
	 */
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
					if(f.getType().equals(fleet.get(i).getType()) && f.isDestroyed() == false) {
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
	/**
	 * muestra los datos de la nave en una cadena
	 * @return devuelve la cadena
	 */
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "] " + this.myFleet();  
	}
}
