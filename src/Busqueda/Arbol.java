package Busqueda;

import Main.Casilla;

public class Arbol {
	Casilla raiz;
	Arbol arriba;
	Arbol abajo;
	Arbol derecha;
	Arbol izquierda;
	
	Arbol(Casilla a){
		raiz = a;
		arriba = null;
		abajo = null;
		derecha = null;
		izquierda = null;
	}
	
	Arbol(){
		raiz = null;
		arriba = null;
		abajo = null;
		derecha = null;
		izquierda = null;
	}
	
	public boolean esVacio(Arbol a) {
		boolean vacio = false;
		if(a.raiz == null) vacio = true;
		return vacio;
	}
}
