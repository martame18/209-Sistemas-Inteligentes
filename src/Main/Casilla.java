package Main;

import robocode.control.RobotSetup;

// en esta clase se guarda la info de cada casilla:
//    si es obstáculo/estado inicial/estado final

public class Casilla {
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
	
}
