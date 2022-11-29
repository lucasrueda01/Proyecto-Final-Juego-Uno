package UnoGame.modelo;

public class Carta {

	private Color color;
	private Tipo tipo;
	
	public Carta(Tipo tipo, Color color) {
		this.tipo = tipo;
		this.color = color;
	}
	
	public Carta(Tipo tipo) { //carta comodin sin color
		this.tipo = tipo;
		this.color = null;
	}

	public Color getColor() {
		return color;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	@Override
	public String toString() {
		String s;
		if (this.color != null) 
			s = tipo.getValor() + " - " + color.getValor();
		else
			s = tipo.getValor();
		return s;
	}
	
}
