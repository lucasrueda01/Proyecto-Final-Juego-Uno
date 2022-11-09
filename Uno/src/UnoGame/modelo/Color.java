package UnoGame.modelo;

public enum Color {
	AZUL("AZUL"), ROJO("ROJO"), AMARILLO("AMARILLO"), VERDE("VERDE");

	private final String valor;

	Color(String v) {
		this.valor = v;
	}

	public String getValor() {
		return valor;
	}
}
