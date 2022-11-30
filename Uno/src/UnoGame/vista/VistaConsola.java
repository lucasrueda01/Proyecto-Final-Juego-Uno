package UnoGame.vista;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import UnoGame.controlador.*;
import UnoGame.modelo.*;

public class VistaConsola implements IVista {
	private Scanner entrada;
	private Controlador controlador;

	public VistaConsola() {
		this.entrada = new Scanner(System.in);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void mostrarMenuPrincipal() {
		System.out.println("########################");
		System.out.println("######### UNO ##########");
		System.out.println("########################");
		System.out.println();
		System.out.println("Selecciona una opcion:");
		System.out.println("1. Agregar jugador");
		System.out.println("2. Comenzar UNO");
		System.out.println("3. Tabla de puntuaciones");
		System.out.println("0. Salir");
		System.out.println();
		this.mostrarListaJugadores(this.controlador.getListaJugadores());
	}

	public void iniciar() throws Exception {
		boolean salir = false;
		while (!salir) {
			this.limpiarPantalla();
			this.mostrarMenuPrincipal();
			String opcion = this.entrada.nextLine();
			switch (opcion) {
			case "1":
				this.formularioJugador();
				break;
			case "2":
				if (this.controlador.getListaJugadores().size() > 1)
					this.iniciarJuego();
				else {
					System.out.println("ERROR - No hay suficientes jugadores para comenzar");
					this.esperarTecla();
					this.limpiarPantalla();
				}
				break;
			case "3":
				this.tablaPuntuaciones(this.controlador.getListaJugadores());
				break;
			case "0":
				salir = true;
				System.out.println("Gracias por jugar!");
				break;
			default:
				System.out.println("-Opcion invalida-");
			}
		}
	}

	public void formularioJugador() {
		System.out.print("Ingrese el nombre: ");
		String nombre = this.entrada.nextLine();
		this.controlador.agregarJugador(nombre);
	}

	public void mostrarListaJugadores(ArrayList<Jugador> jugadores) {
		for (Jugador j : jugadores) {
			System.out.println("Jugador " + j.getNumeroJugador() + " : " + j.getNombre());
		}
	}

	public void mostrarManoJugador(Jugador jugador) {
		System.out.println("----------------Mano de " + jugador.getNombre() + "---------------");
		for (int i = 0; i < jugador.getMano().size(); i++) {
			System.out.println((i + 1) + ". [" + jugador.getCarta(i) + "]");
		}
		System.out.println("--------------------------------------------");
	}

	public void iniciarJuego() throws Exception {
		boolean terminado = false;
		this.limpiarPantalla();
		System.out.println("Repartiendo cartas...");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Comienza " + getJugador(this.controlador.getTurno() - 1).getNombre());
		TimeUnit.SECONDS.sleep(2);
		this.limpiarPantalla();
		boolean cartaSacada = false;
		boolean cartasExtra;
		boolean dijoUno = false;
		while (!terminado) {
			this.limpiarPantalla();
			Jugador jugadorActual = getJugador(this.controlador.getTurno() - 1);
			mostrarManoJugador(jugadorActual);
			cartasExtra = this.controlador.hayCartasExtra();
			if (cartasExtra) {
				System.out.println(
						"0. RECIBIR LAS " + this.controlador.getCantidadCartasExtra() + " CARTAS Y PASAR TURNO");
				this.controlador.setIgnorar(true);
			} else {
				if (!cartaSacada)
					System.out.println("0. SACAR CARTA DEL MAZO");
				else
					System.out.println("0. PASAR TURNO");
			}
			System.out.println("");
			System.out.println("POZO: [" + this.controlador.getTopePozo() + "]");
			System.out.println("COLOR: [" + this.controlador.getColorActual().getValor() + "]");
			System.out.println("");
			System.out.print("Elija una carta: ");
			String opcion = this.validarEntrada();
			int numero = Integer.parseInt(opcion);
			if ((numero >= 1) && (numero <= jugadorActual.getMano().size())) {
				this.controlador.descartarCarta(jugadorActual, numero - 1);
				cartaSacada = false;
			} else {
				if ((numero == 0) && (cartasExtra)) {
					this.controlador.agregarCartasExtra(jugadorActual);
					this.controlador.cambiarTurno();
				} else {
					if ((numero == 0) && (!cartaSacada)) {
						this.controlador.sacarParaJugador(jugadorActual);
						cartaSacada = true;
					} else {
						if ((numero == 0) && (cartaSacada)) {
							this.controlador.cambiarTurno();
							cartaSacada = false;
						} else {
							System.out.println("-ERROR! Opcion Invalida-");
							this.esperarTecla();
						}
					}
				}
			}
			this.controlador.setIgnorar(false);
			if (jugadorActual.getMano().size() == 0) {
				this.controlador.calcularPuntaje(jugadorActual);
				this.imprimirCartel(jugadorActual.getNombre() + " ha ganado con " + jugadorActual.getPuntaje() + " puntos! Felicidades!!");
				terminado = true;
			}
		}

	}

	public Colores elegirNuevoColor() {
		System.out.println("");
		Colores nuevoColor = null;
		System.out.println("1. ROJO");
		System.out.println("2. AMARILLO");
		System.out.println("3. AZUL");
		System.out.println("4. VERDE");
		System.out.println();
		System.out.print("Elija un nuevo color: ");
		String opcion = entrada.nextLine();
		switch (opcion) {
		case "1":
			nuevoColor = Colores.ROJO;
			break;
		case "2":
			nuevoColor = Colores.AMARILLO;
			break;
		case "3":
			nuevoColor = Colores.AZUL;
			break;
		case "4":
			nuevoColor = Colores.VERDE;
			break;
		}
		return nuevoColor;
	}

	public Jugador getJugador(int i) {
		return this.controlador.getListaJugadores().get(i);
	}

	public void limpiarPantalla() {
		for (int i = 0; i < 25; i++)
			System.out.println();
	}

	public void imprimirCartel(String s) {
		System.out.println("");
		System.out.println(s);
		this.esperarTecla();
	}

	public void esperarTecla() {
		System.out.println("Presione cualquier tecla para continuar...");
		entrada.nextLine();
		this.limpiarPantalla();
	}

	public String validarEntrada() {
		String tecla = entrada.nextLine();
		while (tecla.equals("")) {
			tecla = entrada.nextLine();
		}
		return tecla;
	}

	public void tablaPuntuaciones(ArrayList<Jugador> jugadores) {
		boolean salir = false;
		while (!salir) {
			this.limpiarPantalla();
			if (jugadores.size() > 0) {
				System.out.println("NOMBRE ----- PUNTUACION");
				for (Jugador j : jugadores) {
					System.out.println(j.getNombre() + " ------- " + j.getPuntaje() + "pts.");
				}
			} else {
				System.out.println("No hay jugadores...");
			}
			System.out.println("0. VOLVER");
			String opcion = entrada.nextLine();
			switch (opcion) {
			case "0":
				salir = true;
				break;
			default:
				break;
			}
		}
	}

}
