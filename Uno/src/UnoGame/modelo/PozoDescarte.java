package UnoGame.modelo;

public class PozoDescarte extends Pila {
	private Color color;
	private int cartasExtra; // Acumula las cartas que suman +2 o +4
	private boolean ignorar; // Bandera para controlar cuando se pueden acumular +2 y +4

	public PozoDescarte() {
		super();
		this.cartasExtra = 0;
		this.ignorar = false;
	}

	public void setIgnorar(boolean ignorar) {
		this.ignorar = ignorar;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getCartasExtra() {
		return cartasExtra;
	}

	public void setCartasExtra(int cartasExtra) {
		this.cartasExtra = cartasExtra;
	}

	public boolean hayCartasExtra() {
		return (this.cartasExtra > 0);
	}

	public Carta consultarTope() {
		return this.pilaCartas.peek();
	}

	public void add(Carta c) {
		super.add(c);
		if (c.getColor() != null)
			this.color = c.getColor();
	}
	

	public boolean compararCarta(Carta carta) {
		boolean b = false;
		if ((carta.getColor() == null) && (!ignorar)) { // Si la carta es un comodin
			b = true;
		} else {
			Carta cartaPozo = this.consultarTope();
			switch (cartaPozo.getTipo()) {
			case CAMBIA_COLOR:
				if ((!ignorar) && (carta.getColor().equals(this.color)))
					b = true;
				break;
			case CAMBIA_COLOR_ROBA_CUATRO:
				if ((!ignorar) && (carta.getColor().equals(this.color)))  {
					b = true;
				} else {
					if (carta.getTipo().equals(Tipo.CAMBIA_COLOR_ROBA_CUATRO)) {
						b = true;
					}
				}
				break;
			case ROBA_DOS:
				if (!ignorar) {
					if ((carta.getColor().equals(this.color)) || (carta.getTipo().equals(Tipo.ROBA_DOS)))
						b = true;
				} else {
					if (carta.getTipo().equals(Tipo.ROBA_DOS))
						b = true;
				}
				break;
			default:
				if (((carta.getTipo().equals(cartaPozo.getTipo())) || (carta.getColor().equals(this.color))))
					b = true;
				break;
			}
		}
		return b;
	}
}
