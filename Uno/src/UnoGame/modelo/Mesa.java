package UnoGame.modelo;

import java.util.ArrayList;

import UnoGame.observer.*;

public class Mesa implements Observable {
	private ArrayList<Jugador> jugadores;
	private MazoPrincipal mazoPrincipal;
	private PozoDescarte pozoDescarte;
	private boolean sentidoNormal = true;
	private int turnoActual;
	private final int cartasIniciales = 7;

	private ArrayList<Observador> observadores;

	public Mesa() {
		jugadores = new ArrayList<Jugador>();
		mazoPrincipal = new MazoPrincipal();
		pozoDescarte = new PozoDescarte();
		this.turnoActual = 1; // el primer jugador es el que empieza
		this.pozoDescarte.add(this.mazoPrincipal.sacar());
		this.observadores = new ArrayList<>();
	}

	public void addJugador(String nombre) {
		Jugador j = new Jugador(nombre);
		this.repartir(j, cartasIniciales);
		jugadores.add(j);
		this.notificar(Eventos.JUGADOR_AGREGADO);
	}

	public void cambiarSentido() {
		if (this.sentidoNormal) {
			this.sentidoNormal = false;
		} else {
			this.sentidoNormal = true;
		}
	}

	public int getTurnoActual() {
		return turnoActual;
	}
	
	public ArrayList<Jugador> getListaJugadores() {
		return jugadores;
	}

	public PozoDescarte getPozoDescarte() {
		return pozoDescarte;
	}	

	public void cambiarTurno() {
		if (this.sentidoNormal) {
			if (this.turnoActual != this.jugadores.size()) {
				this.turnoActual++;
			} else {
				this.turnoActual = 1;
			}
		} else {
			if (this.turnoActual != 1) {
				this.turnoActual--;
			} else {
				this.turnoActual = this.jugadores.size();
			}
		}
	}

	public void cambiarTurnoSalteandoUno() {
		if (this.sentidoNormal) {
			if (this.turnoActual == this.jugadores.size()) {
				this.turnoActual = 2;
			} else {
				if (this.turnoActual == this.jugadores.size() - 1) {
					this.turnoActual = 1;
				} else {
					this.turnoActual += 2;
				}
			}
		}
		if (!this.sentidoNormal) {
			if (this.turnoActual == 1) {
				this.turnoActual = this.jugadores.size() - 1;
			} else {
				if (this.turnoActual == 2) {
					this.turnoActual = this.jugadores.size();
				} else {
					this.turnoActual -= 2;
				}
			}
		}
	}

	public void repartir(Jugador j, int n) {
		for (int i = 0; i < n; i++) {
			j.addCarta(mazoPrincipal.sacar());
		}
	}

	public void reiniciar() {
		while (!this.pozoDescarte.estaVacia()) 
			this.mazoPrincipal.add(this.pozoDescarte.sacar());
		this.mazoPrincipal.mezclar();
		this.pozoDescarte.add(this.mazoPrincipal.sacar());
	}
	
	public void sacarParaJugador(Jugador j) {
		j.addCarta(this.mazoPrincipal.sacar());
		if (this.mazoPrincipal.estaVacia()) 
			this.reiniciar();
	}

	public void agregarCartasExtra(Jugador j) { 
		int cartasExtra = this.pozoDescarte.getCartasExtra();
		while (cartasExtra != 0) {
			j.addCarta(this.mazoPrincipal.sacar());
			cartasExtra--;
		}
		this.pozoDescarte.setCartasExtra(0);
	}

	public void descartarCarta(Jugador j, int iCarta) {
		Carta cartaActual = j.getCarta(iCarta);
		int cartasExtra = this.pozoDescarte.getCartasExtra();
		if (this.pozoDescarte.compararCarta(cartaActual)) {
			switch (cartaActual.getTipo()) {
			case CAMBIO_SENTIDO:
				this.agregarCartasExtra(j);
				this.cambiarSentido();
				this.cambiarTurno();
				break;
			case SALTEA_TURNO:
				this.agregarCartasExtra(j);
				this.cambiarTurnoSalteandoUno();
				break;
			case CAMBIA_COLOR:
				this.agregarCartasExtra(j);
				this.notificar(Eventos.CAMBIAR_COLOR);
				this.cambiarTurno();
				break;
			case CAMBIA_COLOR_ROBA_CUATRO:
				if (this.pozoDescarte.consultarTope().getTipo().equals(Tipos.CAMBIA_COLOR_ROBA_CUATRO)) {
					cartasExtra += 4;
					this.pozoDescarte.setCartasExtra(cartasExtra);
				} else {
					this.agregarCartasExtra(j);
					cartasExtra = 4;
					this.pozoDescarte.setCartasExtra(cartasExtra);
				}
				this.notificar(Eventos.CAMBIAR_COLOR);
				this.cambiarTurno();
				break;
			case ROBA_DOS:
				if (this.pozoDescarte.consultarTope().getTipo().equals(Tipos.ROBA_DOS)) {
					cartasExtra += 2;
					this.pozoDescarte.setCartasExtra(cartasExtra);
				} else {
					this.agregarCartasExtra(j);
					cartasExtra = 2;
					this.pozoDescarte.setCartasExtra(cartasExtra);
				}
				this.cambiarTurno();
				break;
			default:
				this.agregarCartasExtra(j);
				this.cambiarTurno();
				break;
			}
			j.quitarCarta(iCarta);
			this.pozoDescarte.add(cartaActual);
			this.notificar(Eventos.CARTA_DESCARTADA);
		} else {
			this.notificar(Eventos.CARTA_INVALIDA);
		}
	}

	public int calcularPuntaje(Jugador jugador) {
		int puntos = 0;
		for (Jugador j: jugadores) {
			for (Carta c: j.getMano()) {
				switch (c.getTipo()) {
				case ROBA_DOS: 
				case CAMBIO_SENTIDO:
				case SALTEA_TURNO:
					puntos += 20;
					break;
				case CAMBIA_COLOR:
				case CAMBIA_COLOR_ROBA_CUATRO:
					puntos += 50;
					break;
				default:
					break;
				}
			}
		}
		this.jugadores.get(jugador.getNumeroJugador() - 1).sumarPuntos(puntos);
		return puntos;
	}
	
	// metodos para Observer

	@Override
	public void notificar(Eventos evento) {
		for (Observador observador : this.observadores) {
			observador.actualizar(evento, this);
		}
	}

	@Override
	public void agregarObservador(Observador observador) {
		this.observadores.add(observador);
	}

}
