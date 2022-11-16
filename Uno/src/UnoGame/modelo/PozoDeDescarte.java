package UnoGame.modelo;

import java.util.Stack;

public class PozoDeDescarte {
	private Stack<Carta> pozo;
	
	public PozoDeDescarte() {
		pozo = new Stack<Carta>();
	}
	
	public Carta consultarTope() {
		return this.pozo.peek();
	}
	
	public void add(Carta c) {
		this.pozo.add(c);
	}
	
	public boolean compararMazoDescarte(Carta c) {
		boolean b = false;
		Carta c2 = this.consultarTope();
		if ((c.getTipo().equals(c2.getTipo())) || (c.getColor().equals(c2.getColor()))) {
			b = true;
		}
		return b;
	}
}
