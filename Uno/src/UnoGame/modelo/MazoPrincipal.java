package UnoGame.modelo;

import java.util.Collections;

public class MazoPrincipal extends Pila {
	
	public MazoPrincipal() {
		super();
		this.crear();
		this.mezclar();
	}
	
	private void crear() { // crea la cantidad correspondiente de cada tipo de carta
		for (int color = 0; color < 4; color++) {
			for (int numero = 1; numero <= 9; numero++) { // cartas del 1 al 9, dos por color de cada una
				pilaCartas.add(new Carta(Tipos.values()[numero], Colores.values()[color]));
				pilaCartas.add(new Carta(Tipos.values()[numero], Colores.values()[color]));
			}
			pilaCartas.add(new Carta(Tipos.CERO, Colores.values()[color])); // cuatro cartas de 0
			for (int i = 0; i < 2; i++) { // cartas especiales, dos por color
				pilaCartas.add(new Carta(Tipos.ROBA_DOS, Colores.values()[color]));
				pilaCartas.add(new Carta(Tipos.CAMBIO_SENTIDO, Colores.values()[color]));
				pilaCartas.add(new Carta(Tipos.SALTEA_TURNO, Colores.values()[color]));	
			}
		}
		for (int i = 0; i < 4; i++) { // cartas comodines sin color, cuatro de cada una
			pilaCartas.add(new Carta(Tipos.CAMBIA_COLOR));
			pilaCartas.add(new Carta(Tipos.CAMBIA_COLOR_ROBA_CUATRO));
		}
	}
	
	public void mezclar() {
		Collections.shuffle(this.pilaCartas);
		while (this.pilaCartas.peek().getColor() == null) // evito que una carta comodin sea la primera en salir
			Collections.shuffle(this.pilaCartas);
		
	}	
	
}

