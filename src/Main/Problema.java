package Main;

import java.util.Random;

public class Problema {
	
	// Construimos Estado Inicial, Final y Malla de obstáculos en esta clase.
	
	private Casilla campoBatalla [] [];
	
	private int semilla;
	private int numObstaculos;
	
	//m�todo para comprobar si una casilla ya ha sido seleccionada antes, para no sobreponer obst�culos
	private boolean casillaOcupada (int fila, int columna) {
		boolean ocupada = false;
		
		if(campoBatalla[fila][columna].ocupada()) {
			ocupada = true;
		}
		
		return ocupada;
	}
	
	//m�todo para inicializar las casillas del campo de batalla
	
	private void inicializarCampo(Configuracion cfg) {
		campoBatalla = new Casilla [cfg.getNumFila()] [cfg.getNumColumna()];
		for (int i=0; i<cfg.getNumFila(); i++) {
			for (int j=0; j<cfg.getNumColumna(); j++) {
				Casilla aux = new Casilla(i,j);
				campoBatalla [i][j] = aux;
			}
		}
	}
	
	public Problema (Configuracion cfg){
		semilla = cfg.getSemilla();
		numObstaculos = cfg.getNumObstaculos();
		
		Random rn = new Random(semilla);
		
		inicializarCampo(cfg);
		
		int porcolocar = numObstaculos+2; //variable que indica las casillas que quedan por colocar
		int cfila, ccol; //variables auxiliares para el bucle
		//el bucle se va a repetir mientras que no se hayan repartido todos los obst�culos y los estados inicial y final
		while (porcolocar>0) {
			//genera una nueva casilla aleatoria:
			cfila = rn.nextInt(cfg.getNumFila());    //0-11
			ccol = rn.nextInt(cfg.getNumColumna());  //0-15
			if ((porcolocar-numObstaculos)==2) { //si es la primera casilla que coloca, se convierte en la inicial
				campoBatalla[cfila][ccol].pinicial = true;
				porcolocar--;
			}
			if (!casillaOcupada(cfila,ccol)) {  //comprueba que la casilla generada no est� ocupada
				if((porcolocar-numObstaculos)==1) {  //si es la segunda casilla que coloca, se convierte en la final
					campoBatalla[cfila][ccol].pfinal = true;
					porcolocar--;
				} else {   //si ya se han colocado las casillas inicial y final, entonces lo colocamos como obst�culo
					campoBatalla[cfila][ccol]._esObstaculo = true;
					porcolocar--;
				}
			}
		}
		printearCampo (cfg);
		
	}
	
	//m�todo para mostrar por pantalla el campo de batalla
	private void printearCampo(Configuracion cfg) { 
		int pfilas = cfg.getNumFila();
		int pcolums = cfg.getNumColumna();
		
		for (int y=-1; y<pfilas; y++) {      //y es la columna en la que nos encontramos +1, porque la primera es para las coordenadas
			for (int x=-1; x<pcolums; x++) { //x es la fila en la que nos encontramos +1, porque la primera es para las coordenadas
				
				if(x == -1 && y == -1) System.out.print("    ");
				else if(y == -1){  //la primera fila es para imprimir las coordenadas:
					if(x<=9) System.out.print(" 0"+x+" ");  //incluyo un 0 en las decenas de los menores de 10 para que el tama�o de la tabla sea uniforme
					else System.out.print(" "+x+" ");
				} else if (x == -1) { //la primera columna es para imprimir las coordenas:
					if(y<=9) System.out.print(" 0"+y+" ");
					else System.out.print(" "+y+" ");
				
				} else if (casillaOcupada(y, x)) {  //si la casilla est� ocupada comprueba si es obst�culo, inicial o final
					if (campoBatalla[y][x].pinicial == true){ //si es la casilla inicial imprime "I"
						System.out.print(" I  ");
					} else if (campoBatalla[y][x].pfinal == true) {  //si es la casilla final imprime "F"
						System.out.print(" F  ");
					} else {  //si es un obst�culo imprime "X"
						System.out.print(" X  ");
					}
				} else {
					System.out.print(" -  ");
				}
			}
			System.out.println("\n");
		}
	}
}
