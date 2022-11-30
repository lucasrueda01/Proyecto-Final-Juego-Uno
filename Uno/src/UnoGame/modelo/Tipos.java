package UnoGame.modelo;

public enum Tipos {
	CERO("0"), UNO("1"), DOS("2"), TRES("3"), CUATRO("4"), CINCO("5"), SEIS("6"), SIETE("7"), OCHO("8"), NUEVE("9"),
	ROBA_DOS("ROBA 2"), CAMBIO_SENTIDO("CAMBIO SENTIDO"), SALTEA_TURNO("SALTEO TURNO"), CAMBIA_COLOR("CAMBIO COLOR"),
	CAMBIA_COLOR_ROBA_CUATRO("CAMBIO COLOR Y ROBA 4");

	private final String valor;

	Tipos(String v) {
		this.valor = v;
	}

	public String getValor() {
		return valor;
	}
}
