package Busqueda;

import java.util.Iterator;
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
	
	@Override
	public String getName() {
		return "Amplitud";
	}
	
	public void buscar() {
		Casilla aux = null;
		while (!abiertos.isEmpty()) { 
			pasoAbiertoCerrado(aux);    //paso 2.1 - 2.2
			// comprobamos si aux es la casilla final  paso 2.3
			if (aux._fila == nuevoProblema.getFinale()._fila && aux._columna == nuevoProblema.getFinale()._columna) {
				// devuelveCamino(aux);
			} else {
				// expandir(aux);
			}
		}
	}
	
	// método para pasar el primer nodo de abiertos a cerrados (y lo guarda en la casilla dada como parámetro)
	private void pasoAbiertoCerrado(Casilla aux) {
		aux = abiertos.get(0);
		cerrados.add(abiertos.get(0));
		abiertos.remove(0);
	}
	
	// método para devolver el camino desde el inicio a la casilla dada pasando por sus nodos intermedios
	private void devuelveCamino(Casilla aux) {
		Accion ac;
		if (aux.padre != null) {
			devuelveCamino(aux.padre);
			ac = new Accion(aux.padre, aux);
			camino.add(ac);
		} 
	} 
	
	private void expandir (Casilla aux) {
		// comprobamos si colinda con paredes
		Casilla nuevacas;
		
		// para expandir a la izquierda: 
		if (!nuevoProblema.campoBatalla[aux._fila -1][aux._columna]._esObstaculo && aux._fila != 0) {
			nuevacas = new Casilla (aux._fila -1, aux._columna, aux);
			if (!compruebaAbiertosCerrados(nuevacas)) {
				abiertos.add(nuevacas);
			} else {
				// PASO 2.5.2 DECIDIR QUÉ CAMINO CONSERVA EL ÁRBOL
			}
		}
	}
	
	// método para comprobar que la casilla sea nueva: si es false lo es, si es true ya estaba en el árbol
	private boolean compruebaAbiertosCerrados(Casilla aux) {
		boolean sta = false;
		Iterator<Casilla> it = abiertos.iterator();
		
		while (it.hasNext() && !sta) {
			if(it.next()._fila == aux._fila && it.next()._columna == aux._columna) {
				sta = true;
			}
		}
		
		return sta;
	}
}
