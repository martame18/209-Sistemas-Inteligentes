package Busqueda;

public class Accion {
	public enum accion {izquierda,derecha,arriba,abajo};
	
	accion action;
	
	Accion(accion a){
		action = a;
	}
	/*
	public void andar () {
		// 4 acciones
		switch (action) {
		case arriba: grados = 0; break;
		case derecha: grados = 90; break;
		case abajo: grados = 180; break;
		case izquierda: grados = 270; break;
		}
		turnRight( normalRelativeAngleDegrees ( grados - getHeading()) );
	}
	*/
}
