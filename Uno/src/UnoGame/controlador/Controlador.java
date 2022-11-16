package UnoGame.controlador;

import UnoGame.modelo.Mesa;
import UnoGame.vista.VistaConsola;

public class Controlador {
	private Mesa modelo;
	private VistaConsola vista;

	public Controlador(Mesa modelo, VistaConsola vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void agregarJugador(String nombre) {
		this.modelo.addJugador(nombre);
	}
}
