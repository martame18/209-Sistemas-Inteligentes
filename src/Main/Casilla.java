package Main;

import robocode.control.RobotSetup;

// en esta clase se guarda la info de cada casilla:
//    si es obstáculo/estado inicial/estado final

public class Casilla implements Comparable{
	public int _fila, _columna;
	public boolean _esObstaculo = false;
	public boolean pinicial = false;
	public boolean pfinal = false;
	public Casilla padre;
	
	public Casilla (int filaP, int columnaP){
		_fila 		= filaP;
		_columna 	= columnaP;
		padre = null;
	}
	public Casilla (int filaP, int columnaP, Casilla p){
		_fila 		= filaP;
		_columna 	= columnaP;
		padre = p;
	}
	
	public boolean ocupada() {
		if (_esObstaculo || pinicial || pfinal) return true;
		else return false;
	}
	@Override
	public int compareTo(Object o) {
		int igual = 0;
		Casilla oc = (Casilla)o;
		if (oc._fila != this._fila) igual = 1;
		else if (oc._columna != this._columna) igual = 1;
		return igual;
	}
	
}
