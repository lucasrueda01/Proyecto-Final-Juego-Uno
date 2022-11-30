package Uno;

import UnoGame.modelo.*;
import UnoGame.controlador.*;
import UnoGame.vista.*;


public class UnoMain {

	public static void main(String[] args) throws Exception {
		Mesa modelo = new Mesa();
		VistaConsola vista = new VistaConsola();
		Controlador controlador = new Controlador(modelo, vista);
		vista.iniciar();
	}

}
