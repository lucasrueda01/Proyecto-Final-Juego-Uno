package UnoGame.modelo;

public enum Tipo {
	CERO("0"), UNO("1"), DOS("2"), TRES("3"), CUATRO("4"), CINCO("5"), SEIS("6"), SIETE("7"), OCHO("8"), NUEVE("9"),
	ROBA_DOS("ROBA DOS"), CAMBIO_SENTIDO("CAMBIO SENTIDO"), SALTEA_TURNO("SALTEO TURNO"), CAMBIA_COLOR("CAMBIO COLOR"),
	CAMBIA_COLOR_ROBA_CUATRO("CAMBIO COLOR Y ROBA CUATRO");

	private final String valor;

	Tipo(String v) {
		this.valor = v;
	}

	public String getValor() {
		return valor;
	}
}
