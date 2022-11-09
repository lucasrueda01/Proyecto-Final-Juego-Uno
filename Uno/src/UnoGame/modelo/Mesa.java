package UnoGame.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Mesa {
	Stack<Carta> mazoPrincipal;
	Stack<Carta> mazoDescarte;
	ArrayList<Jugador> jugadores;

	public Mesa() {
		mazoPrincipal = new Stack<Carta>();
		jugadores = new ArrayList<Jugador>();
		mazoDescarte = new Stack<Carta>();
		this.crearMazoPrincipal();
		this.mezclarMazoPrincipal();
	}

	public void addJugador(String nombre) {
		Jugador j = new Jugador(nombre);
		jugadores.add(j);
	}

	public int cantJugadores() {
		return jugadores.size();
	}

	public Carta sacarDeMazoPrincipal() {
		return mazoPrincipal.pop();
	}
	
	public Carta consultarMazoDescarte() {
		return this.mazoDescarte.peek();
	}
	
	public void addMazoDescarte(Carta c) {
		this.mazoDescarte.add(c);
	}

	public void crearMazoPrincipal() { // crea la cantidad correspondiente de cada tipo de carta
		for (int color = 0; color < 4; color++) {
			for (int numero = 1; numero <= 9; numero++) { // cartas del 1 al 9, dos por color de cada una
				mazoPrincipal.add(new Carta(Tipo.values()[numero], Color.values()[color]));
				mazoPrincipal.add(new Carta(Tipo.values()[numero], Color.values()[color]));
			}
			mazoPrincipal.add(new Carta(Tipo.CERO, Color.values()[color])); // cuatro cartas de 0
			for (int i = 0; i < 2; i++) { // cartas especiales, dos por color
				mazoPrincipal.add(new Carta(Tipo.ROBA_DOS, Color.values()[color]));
				mazoPrincipal.add(new Carta(Tipo.CAMBIO_SENTIDO, Color.values()[color]));
				mazoPrincipal.add(new Carta(Tipo.SALTEA_TURNO, Color.values()[color]));	
			}
		}
		for (int i = 0; i < 4; i++) { // cartas comodines sin color, cuatro de cada una
			mazoPrincipal.add(new Carta(Tipo.CAMBIA_COLOR));
			mazoPrincipal.add(new Carta(Tipo.CAMBIA_COLOR_ROBA_CUATRO));
		}
	}
	
	public void mezclarMazoPrincipal() {
		Collections.shuffle(mazoPrincipal);
	}
	
	public void mostrarmazo() { //test
		for (Carta c: mazoPrincipal) {
			System.out.println(c.mostrarCarta());
		}
	}
	
	public void repartir(int cantidad) {
		for (Jugador jugador: jugadores) {
			for (int i = 0; i < cantidad; i++) {
				jugador.addCarta(mazoPrincipal.pop());
			}
		}
	}
		
	public boolean compararMazoDescarte(Carta c) {
		boolean b = false;
		Carta c2 = this.consultarMazoDescarte();
		if ((c.getTipo().equals(c2.getTipo())) || (c.getColor().equals(c2.getColor()))) {
			b = true;
		}
		return b;
	}
		
		
	
		
		
		
	
}
