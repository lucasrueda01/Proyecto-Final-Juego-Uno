package UnoGame.observer;

import UnoGame.modelo.Eventos;
import UnoGame.observer.Observable;

public interface Observador {
	public void actualizar(Eventos evento, Observable observado);
}
