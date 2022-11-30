package UnoGame.modelo;

public class Carta {

	private Colores color;
	private Tipos tipo;
	
	public Carta(Tipos tipo, Colores color) {
		this.tipo = tipo;
		this.color = color;
	}
	
	public Carta(Tipos tipo) { //carta comodin sin color
		this.tipo = tipo;
		this.color = null;
	}

	public Colores getColor() {
		return color;
	}

	public Tipos getTipo() {
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
