package UnoGame.modelo;

import java.util.Stack;

public class Pila {
	protected Stack<Carta> pilaCartas;
	
	public Pila() {
		pilaCartas = new Stack<Carta>();
	}
	
	public Carta sacar() {
		return pilaCartas.pop();
	}
	
	public boolean estaVacia() {
		return pilaCartas.empty();
	}
	
	public void add(Carta c) {
		pilaCartas.add(c);
	}
}

