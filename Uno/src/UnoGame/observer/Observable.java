package UnoGame.observer;

import UnoGame.modelo.Eventos;
import UnoGame.observer.Observador;

public interface Observable {
	public void notificar(Eventos evento);

	public void agregarObservador(Observador observador);
}
