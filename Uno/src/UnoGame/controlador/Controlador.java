package UnoGame.controlador;

import java.util.ArrayList;

import UnoGame.modelo.*;
import UnoGame.observer.Observable;
import UnoGame.observer.Observador;
import UnoGame.vista.*;

public class Controlador implements Observador {
	private Mesa modelo;
	private VistaConsola vista;

	public Controlador(Mesa modelo, VistaConsola vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
		this.modelo.agregarObservador(this);
	}
	
	public void agregarJugador(String nombre) {
		this.modelo.addJugador(nombre);
	}
	
	public ArrayList<Jugador> getListaJugadores() {
		return this.modelo.getListaJugadores();
	}
	
	public void sacarParaJugador(Jugador j){
		this.modelo.sacarParaJugador(j);
	}
	
	public Carta getTopePozo() {
		return this.modelo.getPozoDescarte().consultarTope();
	}
	
	public int getTurno() {
		return this.modelo.getTurnoActual();
	}
	
	public void cambiarTurno() {
		this.modelo.cambiarTurno();
	}
	
	public void descartarCarta(Jugador j, int iCarta) {
		this.modelo.descartarCarta(j, iCarta);
	}
	
	public Colores getColorActual() {
		return this.modelo.getPozoDescarte().getColor();
	}

	public int getCantidadCartasExtra() {
		return this.modelo.getPozoDescarte().getCartasExtra();
	}
	
	public boolean hayCartasExtra() {
		return this.modelo.getPozoDescarte().hayCartasExtra();
	}
	
	public void agregarCartasExtra(Jugador j) {
		this.modelo.agregarCartasExtra(j);
	}
	
	public void setIgnorar(boolean b) {
		this.modelo.getPozoDescarte().setIgnorar(b);
	}
	
	public void calcularPuntaje(Jugador j) {
		this.modelo.calcularPuntaje(j);
	}
	
	@Override
	public void actualizar(Eventos evento, Observable observado) {
		switch (evento) {
			case JUGADOR_AGREGADO:
				this.vista.imprimirCartel("-Jugador agregado!-");
				break;
			case CARTA_DESCARTADA:
				this.vista.imprimirCartel("-Carta descartada!-");
				break;
			case CARTA_INVALIDA:
				this.vista.imprimirCartel("-ERROR... Carta incompatible-");
				break;
			case CAMBIAR_COLOR:
				Colores nuevoColor = this.vista.elegirNuevoColor();
				this.modelo.getPozoDescarte().setColor(nuevoColor);
				break;
		default:
			break;
		}	
	}

}
