package Busqueda;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Main.Casilla;
import Main.Configuracion;
import Main.Problema;


public abstract class Busqueda {
	
	Configuracion cfg;
	Problema nuevoProblema;
	
	Casilla lainicial, lafinal;  //guardamos en estas variables las casillas inicial y final
	
	List <Casilla> abiertos;     //conjunto de nodos abiertos
	HashSet <Casilla> cerrados;  //conjunto de nodos cerrados 
	List <Accion> camino;        //conjunto de pasos necesarios para llegar de la posición inicial a la final
	
	public Busqueda (Problema p){
		cfg = new Configuracion();
		nuevoProblema = new Problema(cfg);
		
		lainicial = nuevoProblema.getIniciale();
		lafinal = nuevoProblema.getFinale();
		
		abiertos = new ArrayList <Casilla>();
		camino = new ArrayList <Accion>();
		cerrados = new HashSet<Casilla>();
		// para asegurar que las listas empiezan vacias
		abiertos.clear();
		cerrados.clear();   
		camino.clear();     
	}
	
	abstract public boolean ejecutar();
	
	public List<Accion> getCamino (){
		return camino;
	}	
	
	public String getName() {
		return null;
	}
		
	public List <Casilla> getAbierto(){
		return abiertos;
	}
	
	public HashSet	<Casilla> getCerrados() {
		return cerrados;
	}
	
	public int getNumNodosExtendidos() {
		return cerrados.size();
	}
	
	public int getNumMaxAbiertos() {
		return abiertos.size();
	}
	
	public int getCoste (){
		int gasto = camino.size();
		return gasto;
	}	
}