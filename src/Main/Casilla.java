package Main;

import robocode.control.RobotSetup;

// en esta clase se guarda la info de cada casilla:
//    si es obstáculo/estado inicial/estado final

public class Casilla {
	public int _fila, _columna;
	public boolean _esObstaculo = false;
	public boolean pinicial = false;
	public boolean pfinal = false;
		
	public Casilla (int filaP, int columnaP){
		_fila 		= filaP;
		_columna 	= columnaP;
	}
	
	public boolean ocupada() {
		if (_esObstaculo || pinicial || pfinal) return true;
		else return false;
	}
	
	// indica las coordenadas en las que tiene que situarse el robot para que esté centrado en la casilla
	public void lugarRobot() {
		RobotSetup cfgRobot = new RobotSetup(_fila+25.0, _columna+25.0, 0.0 );
	}
}
