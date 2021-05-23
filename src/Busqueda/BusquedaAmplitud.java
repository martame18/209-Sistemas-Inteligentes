package Busqueda;

import java.util.List;

import Main.Casilla;
import Main.Problema;

public class BusquedaAmplitud extends Busqueda {
	
	Arbol arb; //Este es el árbol que voy a usar para explorar las casillas
	
	public BusquedaAmplitud(Problema p) {
		super(p);
		
		arb = new Arbol(nuevoProblema.getIniciale());  //El arbol parte de la casilla inicial
		
	}

	@Override
	public boolean ejecutar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
