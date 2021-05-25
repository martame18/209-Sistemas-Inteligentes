package Robot;

import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import Busqueda.Accion;
import Busqueda.BusquedaAmplitud;
import Main.Casilla;
import Main.Configuracion;
import Main.Problema;
import robocode.Robot;

/**
 * 
 */

/**
 * @date 2021-03-09
 * 
 * Plantilla para la definiciÃ³n de un robot en Robocode
 *
 * Configurar Robocode: 1. Options. 2. Preferences 3. Development Options 4. Add
 */
public class RobotBusqueda extends Robot {

	Configuracion cfg;
	Problema problem;
	BusquedaAmplitud ampli;
	
	//The main method in every robot
	public void run() {
		
		cfg = new Configuracion();
		
		System.out.println("Iniciando ejecuciÃ³n del robot");
		
		// Nuestro robot serÃ¡ rojo
		setAllColors(Color.red);

	
		
		//Orientamos inicialmente el robot hacia arriba
		turnRight(normalRelativeAngleDegrees(0 - getHeading()));
		
		//A continuaciÃ³n nuestro robot girarÃ¡ un poco sobre sÃ­ mismo		
		int k = 0;
		while(k < 20){
			turnRight(90);
			k++;
		}
		
		
		// AQUÃ� DEBE:
		//  1. GENERARSE EL PROBLEMA DE BÃšSQUEDA
		//  2. BUSCAR LA SOLUCIÃ“N CON UN ALGORITMO DE BÃšSQUEDA
		//  3. EJECUTAR LA SOLUCIÃ“N ENCONTRADA
		
		problem = new Problema(cfg);
		problem.printearCampo(cfg);
		ampli = new BusquedaAmplitud(problem);
		ejecutarSolucion(ampli);
		imprimirSolucion(ampli);
	}

private void ejecutarSolucion(BusquedaAmplitud ampli) {
		ampli.ejecutar();
		List <Accion> camino = ampli.getCamino();
		Iterator <Accion> it = camino.iterator();
		while (it.hasNext()) {
			andar(it.next());
		}
	}

/***
* EstÃ© mÃ©todo se ejecutarÃ¡ cuÃ¡ndo se pulse el botÃ³n Pintar

*/
	public void onPaint(Graphics2D g) {

	    int columna 	  = cfg.getNumColumna();  //nº de columnas
	    int fila  		  = cfg.getNumFila();     //nº de filas
	    int tamCelda 	  = cfg.getTamCelda();    //pixels de ancho (y largo) de cada celda 
	    int filaPixels 	  = cfg.getNumPixelFila();//cantidad de pixels por fila
	    int columnaPixels = cfg.getnumPixelCol(); //cantidad de pixels por columna
	    
	    g.setColor(Color.white);
	   
	    // Para pintar las filas:
	    for(int i=0; i<=fila; i++) {
	    	g.drawLine(0, (tamCelda*i), filaPixels, (tamCelda*i));
	    }
	    // Para pintar las columnas:
	    for(int j=0; j<=columna; j++) {
	    	g.drawLine((tamCelda*j), 0, (tamCelda*j), columnaPixels);
	    }
	    
	   // dibujar un cuadrado verde en la posición inicial
	   g.setColor(Color.green);
	   g.fillRect ((tamCelda*(problem.getIniciale()._columna + 1) -10),
			    (tamCelda*(problem.getIniciale()._fila + 1) -10), 10, 10);

	   //dibujar un cuadrado rojo en la posición final
	   g.setColor(Color.red);
	   g.fillRect ((tamCelda*(problem.getFinale()._columna + 1) -10),
			    (tamCelda*(problem.getFinale()._fila + 1) -10), 10, 10);
	   
	   //dibujar un cuadrado azul en las casillas abiertas
	   g.setColor(Color.cyan);
	   for(Casilla cas : ampli.getAbierto()) {
		   g.fillRect ((tamCelda*(cas._columna) +5),
				    (tamCelda*(cas._fila ) +5), 5, 5);
	   } 
	 
	 //dibujar un cuadrado naranja en las casillas cerradas
	   g.setColor(Color.orange);
	   for(Casilla cer : ampli.getCerrados()) {
		   g.fillRect ((tamCelda*(cer._columna) +5),
				    (tamCelda*(cer._fila ) +5), 5, 5);
	   } 
	   
	   //dibujar un cuadrado rosa en las casillas del camino
	   g.setColor(Color.magenta);
	   for(Accion ac : ampli.getCamino()) {
		   g.fillRect ((tamCelda*(ac.getCasillaA()._columna) +5),
				    (tamCelda*(ac.getCasillaA()._fila ) +5), 5, 5);
	   } 
	   g.fillRect ((tamCelda*(problem.getFinale()._columna) +5),
			    (tamCelda*(problem.getFinale()._fila ) +5), 5, 5);
	}
	
	// método para hacer que el robot avance según la acción indicada
	public void andar(Accion ac) {
		if (ac.getAction() != null) {  //action es null cuando no puede avanzar en esa dirección (por un obstáculo o pared)
			turnRight(normalRelativeAngleDegrees ( ac.grados() - getHeading()));
			ahead(cfg.getTamCelda()); 
		}
	}

	public void imprimirSolucion(BusquedaAmplitud ampli) {
		System.out.println("     Estados Abiertos:\n");
		for(Casilla cas: ampli.getAbierto()) {
			System.out.println(cas);
		}
		System.out.println("\n\n     Estados Cerrados:\n");
		for(Casilla cer: ampli.getCerrados()) {
			System.out.println(cer);
		}
		System.out.println("\n\n     Camino:\n");
		for(Accion ac: ampli.getCamino()) {
			System.out.println(ac.getCasillaA());
		} System.out.println(problem.getFinale());
		System.out.println("\n\n     Coste: "+ampli.getCoste());
	}
}
