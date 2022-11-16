package UnoGame.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MazoPrincipal {
	private Stack<Carta> mazo;
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MazoPrincipal() {
		mazo = new Stack<Carta>();
		this.crear();
		this.mezclar();
	}
	
	public void crear() { // crea la cantidad correspondiente de cada tipo de carta
		for (int color = 0; color < 4; color++) {
			for (int numero = 1; numero <= 9; numero++) { // cartas del 1 al 9, dos por color de cada una
				mazo.add(new Carta(Tipo.values()[numero], Color.values()[color]));
				mazo.add(new Carta(Tipo.values()[numero], Color.values()[color]));
			}
			mazo.add(new Carta(Tipo.CERO, Color.values()[color])); // cuatro cartas de 0
			for (int i = 0; i < 2; i++) { // cartas especiales, dos por color
				mazo.add(new Carta(Tipo.ROBA_DOS, Color.values()[color]));
				mazo.add(new Carta(Tipo.CAMBIO_SENTIDO, Color.values()[color]));
				mazo.add(new Carta(Tipo.SALTEA_TURNO, Color.values()[color]));	
			}
		}
		for (int i = 0; i < 4; i++) { // cartas comodines sin color, cuatro de cada una
			mazo.add(new Carta(Tipo.CAMBIA_COLOR));
			mazo.add(new Carta(Tipo.CAMBIA_COLOR_ROBA_CUATRO));
		}
	}
	
	public void mezclar() {
		Collections.shuffle(mazo);
	}
	
	public Carta sacar() {
		return mazo.pop();
	}
	
	public void mostrar() { //test
		for (Carta c: mazo) {
			System.out.println(c.mostrarCarta());
		}
	}
}

