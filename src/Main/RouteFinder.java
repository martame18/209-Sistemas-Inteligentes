package Main;


import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;


/**
 * 
 * @date 2021-03-09
 * 
 * Plantilla para la práctica de algoritmos de búsqueda con Robocode 
 * 
 * 
 */

public class RouteFinder {
	/**
	 * Configuración 
	 * 
	 */
	
	static String robocodeLocalization=  "C:\\Robocode"; //Windows
	// static String	 robocodeLocalization= "/Users/joseamontenegromontes/robocode"; //MAC o Linux"
	static String    nombreRobot ="Robot.RobotBusqueda*";
	
	/**
	 * Configuración 	 * 
	 */
	
	Configuracion 	cfg;
	RobocodeEngine  engine;
	BattlefieldSpecification battlefield;
	RobotSpecification[] modelRobots;
	RobotSpecification[] existingRobots;
	RobotSetup		  [] robotSetups ;
	
	
	public  RouteFinder () {
		 cfg = new Configuracion();	

		//ToDo: GENERAR EL MAPA DE OBSTÃ�CULOS Y LAS POSICIONES INICIAL Y FINAL DEL ROBOT
		
		Problema nuevoProblema = new Problema (cfg);			
	}
	
	/******************
	 * Configuración de la batalla
	 */
	
	public void cfgRobocode() {
		
		// Crear el RobocodeEngine desde una la instalaciÃ³n
			 engine = new RobocodeEngine(new java.io.File(robocodeLocalization)); //Ojo configuración 
			 engine.setVisible(true); // Mostrar el simulador de Robocode
			
		// Crear el campo de batalla
			battlefield = new BattlefieldSpecification(cfg.getNumPixelFila(), cfg.getnumPixelCol());
			
				
		// En modelRobots recogemos la especificación de los robots que utilizaremos en la simulación.
			 modelRobots = engine.getLocalRepository(nombreRobot+",sample.SittingDuck");
				
		// Incluiremos un robot sittingDuck por obstáculo, más nuestro propio robot.
			 existingRobots =	new RobotSpecification[cfg.getNumObstaculos()+1];
			 robotSetups 	= 	new RobotSetup[cfg.getNumObstaculos()+1];
	}
	
	/*************************************
	 *  AÃ±ado el Robot de Búsqueda
	 */
	
	public void addRobotBusqueda() {
		
		/*
	     * Creamos primero nuestro propio robot y lo colocamos en la posición inicial del problema,
	     * que debería estar libre de obstáculoa.
		 */
		
		double fila = 125.0, columna = 125.0, arriba = 0.0;			//Temporal
		existingRobots[0] = modelRobots[0];
		robotSetups	  [0] = new RobotSetup(fila,  columna,  arriba);       //orientación inicial	
	}

	/*************************************
	 *  Añado los obstáculos 
	 */
	
	public void addRobotObstaculos() {
		
		/*
	     * Creamos un robot sittingDuck por cada obstáculo.
		 */
		
		double sittingDuckFila = 325.0, sittingDuckColumna = 425.0, arriba = 0.0;	 //Temporal
		
		int obstaculos = cfg.getNumObstaculos();
		
		if (obstaculos > 0) {
			existingRobots[1] 	= modelRobots[1];   //sittingDuck
			robotSetups[1]		= new RobotSetup( sittingDuckFila, sittingDuckColumna ,  arriba);   
			
			System.out.println("Generados "+obstaculos+"  sitting ducks.");
		}
	}
	
	/***************
	 * EjecuciÃ³n
	 */
	public void run () {
		
		/* 
		 * Crear y desarrollar la batalla con los robots antes definidos
		 */
		
		 
		BattleSpecification battleSpec =
				new BattleSpecification(battlefield,
						cfg.numberOfRounds,
						cfg.inactivityTime,
						cfg.gunCoolingRate,
						cfg.sentryBorderSize,
						cfg.hideEnemyNames,
						existingRobots,
						robotSetups);
		
		
		// Ejecutar la simulación el tiempo especificado
		engine.runBattle(battleSpec, true); 
		// Cerrar la simulación
		engine.close();
		// Asegurarse de que la MV de Java se cierra adecuadamente.
		System.exit(0);
	}
	
	/********************************************
	 * Ejercutar
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		RouteFinder practica = new RouteFinder();
		
		
		practica.cfgRobocode();
		practica.addRobotBusqueda();
		practica.addRobotObstaculos();
		practica.run();
		
	
	}
	
}
