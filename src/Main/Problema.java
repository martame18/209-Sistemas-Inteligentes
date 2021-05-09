package Main;

import java.util.Random;

public class Problema {
	
	// Construimos Estado Inicial, Final y Malla de obstÃ¡culos en esta clase.
	
	private Casilla campoBatalla [] [];
	
	private int semilla;
	private int numObstaculos;
	
	//método para comprobar si una casilla ya ha sido seleccionada antes, para no sobreponer obstáculos
	private boolean casillaOcupada (int fila, int columna) {
		boolean ocupada = false;
		
		if((campoBatalla [fila] [columna].isPinicial())||(campoBatalla [fila] [columna].isPfinal())||(campoBatalla [fila] [columna].is_esObstaculo())) {
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
	
	/*
	private void probando(Configuracion cfg) {
		Casilla a = new Casilla(1,0);
		System.out.println("Fila: "+a._fila+" Columna: "+a._columna);
		campoBatalla[1][0] = a;
		System.out.println("Fila: "+campoBatalla[1][0]._fila+" Columna: "+a._columna);
	}
	*/
	public Problema (Configuracion cfg){
		semilla = cfg.getSemilla();
		numObstaculos = cfg.getNumObstaculos();
		
		Random rn = new Random(semilla);
		
		inicializarCampo(cfg);
		
		int porcolocar = numObstaculos+2; //variable que indica las casillas que quedan por colocar
		int cfila, ccol; //variables auxiliares para el bucle
		//el bucle se va a repetir mientras que no se hayan repartido todos los obstáculos y los estados inicial y final
		while (porcolocar>0) {
			//genera una nueva casilla aleatoria:
			cfila = rn.nextInt(cfg.getNumFila());    //0-11
			ccol = rn.nextInt(cfg.getNumColumna());  //0-15
			if ((porcolocar-numObstaculos)==2) { //si es la primera casilla que coloca, se convierte en la inicial
				campoBatalla[cfila][ccol].setPinicial(true);
				porcolocar--;
			}
			if (!casillaOcupada(cfila,ccol)) {  //comprueba que la casilla generada no esté ocupada
				if((porcolocar-numObstaculos)==1) {  //si es la segunda casilla que coloca, se convierte en la final
					campoBatalla[cfila][ccol].setPfinal(true);
					porcolocar--;
				} else {   //si ya se han colocado las casillas inicial y final, entonces lo colocamos como obstáculo
					campoBatalla[cfila][ccol].set_esObstaculo(true);
					porcolocar--;
				}
			}
		}
		printearCampo (cfg);
		
	}
	
	//método para mostrar por pantalla el campo de batalla
	private void printearCampo(Configuracion cfg) { 
		int pfilas = cfg.getNumFila();
		int pcolums = cfg.getNumColumna();
		
		for (int y=0; y<=pfilas; y++) {      //y es la columna en la que nos encontramos +1, porque la primera es para las coordenadas
			for (int x=0; x<=pcolums; x++) { //x es la fila en la que nos encontramos +1, porque la primera es para las coordenadas
				
				if(y == 0){  //la primera fila es para imprimir las coordenadas:
					if(x<=9) System.out.println(" 0"+x+" ");  //incluyo un 0 en las decenas de los menores de 10 para que el tamaño de la tabla sea uniforme
					else System.out.println(" "+x+" ");
				} else if (x == 0) { //la primera columna es para imprimir las coordenas:
					if(y<=9) System.out.println(" 0"+y+" ");
					else System.out.println(" "+y+" ");
				} else if (casillaOcupada(pfilas, pcolums)) {  //si la casilla está ocupada comprueba si es obstáculo, inicial o final
					if (campoBatalla[pfilas][pcolums].isPinicial()){ //si es la casilla inicial imprime "I"
						System.out.println(" I  ");
					} else if (campoBatalla[pfilas][pcolums].isPfinal()) {  //si es la casilla final imprime "F"
						System.out.println(" F  ");
					} else {  //si es un obstáculo imprime "X"
						System.out.println(" X  ");
					}
				} else {
					System.out.println(" -  ");
				}
			
			}
		}
	}
}
