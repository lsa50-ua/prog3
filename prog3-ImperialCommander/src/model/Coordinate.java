package model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Coordinate va a representar una coordenada con dos variables x e y.
 * @author Luis Simón Albarrán 48804855M
 *
 */
public class Coordinate implements Comparable<Coordinate> {
	/**
	 * Primera variable de la coordenada.
	 */
	private int x;
	/**
	 * Segunda variable de la coordenada.
	 */
	private int y;	
	
	/**
	 * Crea una coordenada recibiendo dos parametros.
	 * @param x Primer parametro de la coordenada
	 * @param y Segundo parametro de la coordenada
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	/**
	 * Crea una coordenada recibiendo como parámetro un objeto de la misma clase para igualarle las coordenadas al nuevo objeto.
	 * @param c Objeto de la clase Coordinate
	 */
	public Coordinate(Coordinate c) {
		x = c.x;
		y = c.y;
	}
	/**
	 * @return devuelve la variable x de la coordenada.
	 */
	public int getX() {
		return x;
	}
	/**
	 * @return devuelve la variable y de la coordenada.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sumar las variables x e y del objeto pasado como paramaetro al objeto que invoca el metodo.
	 * @param c objeto de la clase Coordinate
	 * @return Devuelve un nuevo objeto de la clase Coordinate con las variables sumadas.
	 */
	public Coordinate add(Coordinate c) {
		
		Coordinate newC = null;
		
		newC = new Coordinate(x + c.x, y + c.y);
		
		return newC;
		
	}
	/**
	 * Sumar las variables x e y pasadas como paramaetro al objeto que invoca el metodo.
	 * @param x cantidad a sumar a la coordenada x
	 * @param y cantidad a sumar a la coordanada y
	 * @return Devuelve un nuevo objeto de la clase Coordinate con las variables sumadas.
	 */
	public Coordinate  add(int x, int y) {
		Coordinate newC = null;
		newC = new Coordinate(this.x + x, this.y + y);
		return newC;
	}
	/**
	 * Permite imprimir objetos por pantalla.
	 * @return devuelve en formato string como se va a imprimir el objeto.
	 */
	public String toString () {
		
		return "[" + x + "," + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	/**
	 * Compara dos coordenadas
	 * @param otra coordenada que sirve para comprobarla con la implicita
	 * @return devuelve un int con el resultado de la comparacion
	 */
	public int compareTo(Coordinate otra) {
		int comparacion = 0;
		if(x < otra.x) {
			comparacion = -1;
		}
		else {
			if(x > otra.x) {
				comparacion = 1;
			}
			else {
				if(y < otra.y) {
					comparacion = -1;
				}
				else {
					if(y > otra.y) {
						comparacion = 1;
					}
				}
			}
		}
		
		return comparacion;
	}
	/**
	 * Almacena en un TreeSet y lo devuelve con las coordenadas vecinas de la implicita
	 * @return devuelve un TreeSet con las coordenadas de alrededor de la implicita
	 */
	public Set<Coordinate> getNeighborhood() {
		Set<Coordinate> conjunto = null;
		conjunto = new TreeSet<Coordinate>();
		
		for(int nx = x - 1; nx < x + 2; nx ++) {
			for(int ny = y - 1; ny < y + 2; ny ++) {
				if(nx != x || ny != y) {
					conjunto.add(new Coordinate(nx, ny));
				}
			}
		}
		
		return conjunto;
	}
}	
