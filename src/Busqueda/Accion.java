package Busqueda;

import Main.Casilla;
import Main.Configuracion;

public class Accion {
	// 4 acciones
	public enum accion {izquierda,derecha,arriba,abajo};
	
	accion action;
	Casilla casillaA, casillaB;
	Configuracion cfg;
	
	//suponemos que la accion se produce entre casillas colindantes
	public Accion(Casilla a, Casilla b){    // el robot se encuentra en a y quiere moverse a b
		cfg = new Configuracion();
		casillaA = a;   casillaB = b;
		if(comprobarObstaculo(b)){  //si b no está ocupado
			
			// hacemos varias comprobaciones para además asegurarnos de que son casillas colindantes
			// nombre del movimiento en función de cómo se mueve en la pantalla de robocode, no en la matriz del código
			if(b._fila == a._fila) {
				if(b._columna - a._columna == 1) action = accion.derecha;
				else if (a._columna - b._columna == 1) action = accion.izquierda;
			} else if (a._columna == b._columna) {
				if (b._fila - a._fila == 1) action = accion.arriba;
				else if (a._fila - b._fila == 1) action = accion.abajo;
			}
			
		}
		else action = null;
	}

	// este método indica false si la casilla a la que vamos a avanzar es un obstáculo o una pared
	public boolean comprobarObstaculo(Casilla b) {
		boolean pasar = true;
		if(b._esObstaculo || b._fila<0 || b._fila>cfg.getNumFila() || b._columna<0 || b._columna>cfg.getNumColumna()) pasar = false;
		return pasar;
	}
	
	public int grados() {
		int grados = 0;
		// 4 acciones
		switch (action) {
		case arriba: grados = 0; break;
		case derecha: grados = 90; break;
		case abajo: grados = 180; break;
		case izquierda: grados = 270; break;
		}
		return grados;
	}
	
	public accion getAction() {
		return action;
	}
	

	public Casilla getCasillaA() {
		return casillaA;
	}

	public Casilla getCasillaB() {
		return casillaB;
	}
}
