package Busqueda;

import java.util.List;

import Main.Casilla;
import Main.Problema;

public class BusquedaAmplitud extends Busqueda {

	
	public BusquedaAmplitud(Problema p) {
		super(p);
		
	}

	@Override
	public boolean ejecutar() {
		
		abiertos.add(lainicial);  // empezamos con un único nodo en abiertos, la casilla inicial
		
		return false;
	}
	
	public void buscar() {
		Casilla aux = null;
		while (!abiertos.isEmpty()) { 
			pasoAbiertoCerrado(aux);
			// comprobamos si aux es la casilla final
			if (aux._fila == nuevoProblema.getFinale()._fila && aux._columna == nuevoProblema.getFinale()._columna) {
				// devuelveCamino(aux);
			}
		}
	}
	
	// método para pasar el primer nodo de abiertos a cerrados
	private void pasoAbiertoCerrado(Casilla aux) {
		aux = abiertos.get(0);
		cerrados.add(abiertos.get(0));
		abiertos.remove(0);
	}
	/*
	private void devuelveCamino(Casilla aux) {
		if (aux.padre != null) {
			devuelveCamino(aux.padre);
		} 
		camino.add(Accion(aux.padre, aux));
	} */
}
