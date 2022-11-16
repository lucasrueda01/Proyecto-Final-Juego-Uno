package UnoGame.vista;

import java.util.Scanner;

import UnoGame.controlador.Controlador;


public class VistaConsola {
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
		System.out.println("######### -UNO- ########");
		System.out.println("########################");
		System.out.println();
		System.out.println("Selecciona una opción:");
		System.out.println("1. - Agregar jugador");
		System.out.println("2. - Comenzar UNO");
		System.out.println();
		System.out.println("0. - Salir");
	}
	
	public void iniciar() {
		boolean salir = false;
		while (!salir) {
			this.mostrarMenuPrincipal();
			String opcion = this.entrada.nextLine();
			switch (opcion.toLowerCase()) {
			case "1":
				this.formularioJugador();
				break;
			case "2":
				
				break;
			case "0":
				salir = true;
				System.out.println("Adios!");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		}
	}
	
	public void formularioJugador() {
		System.out.print("Ingrese el nombre: ");
		String nombre = this.entrada.nextLine();
		controlador.agregarJugador(nombre);
	}

}
