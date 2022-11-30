package UnoGame.modelo;

import java.util.ArrayList;

public class Jugador {
	private String nombre;
	private int numeroJugador;
	private ArrayList<Carta> mano;
	private static int contadorJugador = 0;
	private int puntaje;
	
	public Jugador(String nombre) {
		this.setNombre(nombre);
		Jugador.contadorJugador++;
		this.numeroJugador = Jugador.contadorJugador;
		mano = new ArrayList<Carta>();
		this.puntaje = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroJugador() {
		return numeroJugador;
	}
	
	public void sumarPuntos(int puntos) {
		this.puntaje += puntos;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}

	public ArrayList<Carta> getMano() {
		return mano;
	}

	public void addCarta(Carta c) {
		mano.add(c);
	}
	
	public Carta quitarCarta(int i) {
		return mano.remove(i);
	}
	
	public Carta getCarta(int i) {
		return mano.get(i);
	}
		
}
