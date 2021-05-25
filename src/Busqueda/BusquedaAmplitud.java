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
		
		abiertos.add(lainicial);  // empezamos con un �nico nodo en abiertos, la casilla inicial
		buscar();
		
		return false;
	}
	
	@Override
	public String getName() {
		return "Amplitud";
	}
	
	public void buscar() {
		Casilla aux = null;
		while (!abiertos.isEmpty() && camino.isEmpty()) { 
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
	
	// m�todo para pasar el primer nodo de abiertos a cerrados (y lo guarda en la casilla dada como par�metro)
	private void pasoAbiertoCerrado(Casilla aux) {
		aux = abiertos.get(0);
		cerrados.add(abiertos.get(0));
		abiertos.remove(0);
	}
	
	// m�todo para devolver el camino desde el inicio a la casilla dada pasando por sus nodos intermedios
	private List <Accion> devuelveCamino(Casilla aux) {
		Accion ac;
		if (aux.padre != null) {
			devuelveCamino(aux.padre);
			ac = new Accion(aux.padre, aux);
			camino.add(ac);
		} 
		return camino;
	} 
	
	// m�todo para expandir la casilla aux, a�adiendo sus colindantes a abiertos
	private void expandir (Casilla aux) {
		Casilla nuevacas;
		//    paso 2.5
		
		//        expandir a la IZQUIERDA:
		// antes de expandir comprobamos si colinda con paredes u obst�culos
		if (aux._fila != 0) {
			if (!nuevoProblema.campoBatalla[aux._fila -1][aux._columna]._esObstaculo) {
				nuevacas = new Casilla (aux._fila -1, aux._columna, aux);
				expandirLado(nuevacas);
			}
		}
		//        expandir a la DERECHA:
		// antes de expandir comprobamos si colinda con paredes u obst�culos
		if (aux._fila != (cfg.getNumFila()-1)) {
			if (!nuevoProblema.campoBatalla[aux._fila +1][aux._columna]._esObstaculo) {
				nuevacas = new Casilla (aux._fila +1, aux._columna, aux);
				expandirLado(nuevacas);
			}
		}
		//       expandir hacia ARRIBA:
		// antes de expandir comprobamos si colinda con paredes u obst�culos
		if (aux._columna != (cfg.getNumColumna()-1)) {
			if (!nuevoProblema.campoBatalla[aux._fila][aux._columna+1]._esObstaculo) {
				nuevacas = new Casilla (aux._fila, aux._columna+1, aux);
				expandirLado(nuevacas);
			}
		}
		//       expandir hacia ABAJO:
		// antes de expandir comprobamos si colinda con paredes u obst�culos
		if (aux._columna != 0) {
			if (!nuevoProblema.campoBatalla[aux._fila][aux._columna-1]._esObstaculo) {
				nuevacas = new Casilla (aux._fila, aux._columna-1, aux);
				expandirLado(nuevacas);
			}
		}
	}
	
	// m�todo para comprobar si la casilla dada es nueva, si lo es se a�ade a abiertos y si no se comprueba si es mejor la previamente explorada o esta
	private void expandirLado(Casilla nuevacas) {
		//    paso 2.5.1
		// si el nodo es nuevo se a�ade a abiertos
		if (compruebaAbiertos(nuevacas) == -1 && compruebaCerrados(nuevacas) == -1) {   
			abiertos.add(nuevacas);    		
		} 
		//    paso 2.5.2
		// al usar una cola FIFO en abiertos, si el nodo ya estaba en el �rbol hay dos opciones:
		// 1. estaba en cerrados: por lo que el nodo habr�a sido explorado antes del actual, lo que implica que su camino tiene menor coste.
		// 2. estaba en abiertos: por lo que el nodo habr�a sido expandido desde el mismo nodo padre que el actual o desde uno anterior,
		//                        en cualquiera de los dos casos tendr�a un coste o menor o igual que el camino del nodo actual.
		// En conclusi�n: si el nodo actual ya se encuentra en abiertos o en cerrados no es necesario sustituirlo.
		
		
	}

	// m�todos para comprobar que la casilla sea nueva: si son -1 lo es, si alguno es distinto ya estaba en el �rbol
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
		return sta;  //devuelve -1 si no est� en abiertos, si est� devuelve el �ndice de su posici�n en la lista
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
