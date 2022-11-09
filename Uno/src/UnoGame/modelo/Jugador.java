package UnoGame.modelo;

import java.util.List;

public class Jugador {
	private String nombre;
	private boolean turno = false;
	private List<Carta> mano;
	
	public Jugador(String nombre) {
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}
	
	public void addCarta(Carta c) {
		mano.add(c);
	}
	
	public Carta quitarCarta(int i) {
		return mano.remove(i);
	}
}
