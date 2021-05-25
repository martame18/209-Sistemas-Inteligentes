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
		buscar();
		
		return false;
	}
	
	@Override
	public String getName() {
		return "Amplitud";
	}
	
	public void buscar() {
		Casilla aux = null;
		while (!abiertos.isEmpty()) { 
			//    paso 2.1 - 2.2
			// se pasar el primer nodo de abiertos a cerrados y se guarda en la casilla aux
			aux = abiertos.get(0);
			cerrados.add(abiertos.get(0));
			abiertos.remove(0);   
			
			//    paso 2.3
			// comprobamos si aux es la casilla final  
			if (nuevoProblema.getFinale().compareTo(aux) == 0) {
				camino = devuelveCamino(aux);
			} else {
			//    paso 2.4
				expandir(aux);
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
	private List <Accion> devuelveCamino(Casilla aux) {
		Accion ac;
		if (aux.padre != null) {
			devuelveCamino(aux.padre);
			ac = new Accion(aux.padre, aux);
			camino.add(ac);
		} 
		return camino;
	} 
	
	private void expandir (Casilla aux) {
		Casilla nuevacas;
		//    paso 2.5
		
		//        expandir a la IZQUIERDA:
		// antes de expandir comprobamos si colinda con paredes u obstáculos
		if (aux._fila != 0) {
			if (!nuevoProblema.campoBatalla[aux._fila -1][aux._columna]._esObstaculo) {
				nuevacas = new Casilla (aux._fila -1, aux._columna, aux);
				expandirLado(nuevacas);
			}
		}
		//        expandir a la DERECHA:
		// antes de expandir comprobamos si colinda con paredes u obstáculos
		if (aux._fila != (cfg.getNumFila()-1)) {
			if (!nuevoProblema.campoBatalla[aux._fila +1][aux._columna]._esObstaculo) {
				nuevacas = new Casilla (aux._fila +1, aux._columna, aux);
				expandirLado(nuevacas);
			}
		}
		//       expandir hacia ARRIBA:
		// antes de expandir comprobamos si colinda con paredes u obstáculos
		if (aux._columna != (cfg.getNumColumna()-1)) {
			if (!nuevoProblema.campoBatalla[aux._fila][aux._columna+1]._esObstaculo) {
				nuevacas = new Casilla (aux._fila, aux._columna+1, aux);
				expandirLado(nuevacas);
			}
		}
		//       expandir hacia ABAJO:
		// antes de expandir comprobamos si colinda con paredes u obstáculos
		if (aux._columna != 0) {
			if (!nuevoProblema.campoBatalla[aux._fila][aux._columna-1]._esObstaculo) {
				nuevacas = new Casilla (aux._fila, aux._columna-1, aux);
				expandirLado(nuevacas);
			}
		}
	}
	
	private void expandirLado(Casilla nuevacas) {
		//    paso 2.5.1
		// si el nodo es nuevo se añade a abiertos
		if (compruebaAbiertos(nuevacas) == -1 && compruebaCerrados(nuevacas) == -1) {   
			abiertos.add(nuevacas);    		
		} else {   
		//    paso 2.5.2
		// si el nodo ya estaba en el árbol comprueba cuál tiene un coste menor
			// si ya el nodo anterior está cerrado es que se ha explorado y expandido antes, por lo que tendrá menor coste y no se sustituye
			// si el nodo anterior está abierto se comprueba cuál tiene menor coste y se descarta el otro
				/*		
			if (compruebaAbiertos (nuevacas) >= 0) {
				List <Accion> caminoA = devuelveCamino(abiertos.get(compruebaAbiertos(nuevacas)));
				List <Accion> caminoB = devuelveCamino(nuevacas);
				if (caminoB.size() > caminoA.size()) {
					abiertos.remove(compruebaAbiertos(nuevacas));
					abiertos.add(nuevacas);
				}
			}*/
						
		}
		
	}

	// métodos para comprobar que la casilla sea nueva: si son false lo es, si alguno es true ya estaba en el árbol
	private int compruebaAbiertos(Casilla aux) {
		int sta = -1;
		int indice = 0;
		Iterator<Casilla> it = abiertos.iterator();
		while (it.hasNext() && sta == -1) {
			if(it.next().compareTo(aux) == 0 ) {
				sta = indice;
			}
			indice ++;
		}
		return sta;
	}
	private int compruebaCerrados(Casilla aux) {
		int sta = -1;
		Iterator<Casilla> it = cerrados.iterator();
		while (it.hasNext() && sta == -1) {
			if(it.next().compareTo(aux) == 0 ) {
				sta = 0;
			}
		}
		return sta;
	}
	
}
