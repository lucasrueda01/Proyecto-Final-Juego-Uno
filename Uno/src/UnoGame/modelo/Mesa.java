package UnoGame.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Mesa {
	private ArrayList<Jugador> jugadores;
	private MazoPrincipal mazo;
	private PozoDeDescarte pozo;
	private final int cartasIniciales = 7;
	private boolean sentidoAsc = true;

	public Mesa() {
		jugadores = new ArrayList<Jugador>();
		mazo = new MazoPrincipal();
		pozo = new PozoDeDescarte();
		this.repartir(cartasIniciales);
		this.jugadores.get(0).setTurno(true); // el primer jugador es el que empieza
	}

	public void addJugador(String nombre) {
		Jugador j = new Jugador(nombre);
		jugadores.add(j);
	}

	public int cantJugadores() {
		return jugadores.size();
	}

	public boolean isSentidoAsc() {
		return sentidoAsc;
	}

	public void setSentidoAsc(boolean sentidoAsc) {
		this.sentidoAsc = sentidoAsc;
	}

	public int siguienteTurno() {
		int i;
		for (i = 0; i < cantJugadores(); i++) {
			if (jugadores.get(i).isTurno()) {
				break;
			}
		}
		return i;
	}

	public void repartir(int n) {
		for (Jugador jugador : jugadores) {
			for (int i = 0; i < n; i++) {
				jugador.addCarta(mazo.sacar());
			}
		}
	}

}
