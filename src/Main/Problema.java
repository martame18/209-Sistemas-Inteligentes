package Main;

import java.util.Random;

import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

// esta clase sirve para crear el problema

public class Problema {
	
	// Construimos Estado Inicial, Final y Malla de obstÃ¡culos en esta clase.
	
	public Casilla campoBatalla [] [];
	
	private int semilla;
	private int numObstaculos;
	// estas variables me sirven para ahorrar un bucle en routefinder
	private Casilla iniciale;
	private Casilla finale;
	
	//método para comprobar si una casilla ya ha sido seleccionada antes, para no sobreponer obstáculos
	private boolean casillaOcupada (int fila, int columna) {
		boolean ocupada = false;
		
		if(campoBatalla[fila][columna].ocupada()) {
			ocupada = true;
		}
		
		return ocupada;
	}
	
	//método para inicializar las casillas del campo de batalla
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
		
		//esta parte es para asignar un lugar a los obstáculos, la posición inicial y final
		int porcolocar = numObstaculos+2; //variable que indica las casillas que quedan por colocar
		int cfila, ccol; //variables auxiliares para el bucle
		//el bucle se va a repetir mientras que no se hayan repartido todos los obstáculos y los estados inicial y final
		while (porcolocar>0) {
			//genera una nueva casilla aleatoria:
			cfila = rn.nextInt(cfg.getNumFila());    //0-11
			ccol = rn.nextInt(cfg.getNumColumna());  //0-15
			if ((porcolocar-numObstaculos)==2) { //si es la primera casilla que coloca, se convierte en la inicial
				campoBatalla[cfila][ccol].pinicial = true;
				porcolocar--;
				iniciale = campoBatalla[cfila][ccol];
			}
			if (!casillaOcupada(cfila,ccol)) {  //comprueba que la casilla generada no esté ocupada
				if((porcolocar-numObstaculos)==1) {  //si es la segunda casilla que coloca, se convierte en la final
					campoBatalla[cfila][ccol].pfinal = true;
					porcolocar--;
					finale = campoBatalla[cfila][ccol];
				} else {   //si ya se han colocado las casillas inicial y final, entonces lo colocamos como obstáculo
					campoBatalla[cfila][ccol]._esObstaculo = true;
					porcolocar--;
				}
			}
		}
		
		printearCampo (cfg);
		
	}
	
	//método para mostrar por pantalla una matriz con el campo de batalla
	private void printearCampo(Configuracion cfg) { 
		int pfilas = cfg.getNumFila();     //   y = fila
		int pcolums = cfg.getNumColumna(); //   x = columna
		
		for (int y=-1; y<pcolums; y++) {      //y es la columna en la que nos encontramos +1, porque la primera es para las coordenadas
			for (int x=-1; x<pfilas; x++) { //x es la fila en la que nos encontramos +1, porque la primera es para las coordenadas
				
				if(x == -1 && y == -1) System.out.print("    ");
				else if(y == -1){  //la primera fila es para imprimir las coordenadas:
					if(x<=9) System.out.print(" 0"+x+" ");  //incluyo un 0 en las decenas de los menores de 10 para que el tamaño de la tabla sea uniforme
					else System.out.print(" "+x+" ");
				} else if (x == -1) { //la primera columna es para imprimir las coordenas:
					if(y<=9) System.out.print(" 0"+y+" ");
					else System.out.print(" "+y+" ");
				
				} else if (casillaOcupada(x, y)) {  //si la casilla está ocupada comprueba si es obstáculo, inicial o final
					if (campoBatalla[x][y].pinicial == true){ //si es la casilla inicial imprime "I"
						System.out.print(" I  ");
					} else if (campoBatalla[x][y].pfinal == true) {  //si es la casilla final imprime "F"
						System.out.print(" F  ");
					} else {  //si es un obstáculo imprime "X"
						System.out.print(" X  ");
					}
				} else {
					System.out.print(" -  ");
				}
			}
			System.out.println("\n");
		}
	}
	public Casilla getIniciale() {
		return iniciale;
	}

	public Casilla getFinale() {
		return finale;
	}

	
}
