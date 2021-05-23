package Busqueda;


import java.util.HashSet;
import java.util.List;

import Main.Casilla;
import Main.Configuracion;
import Main.Problema;


public abstract class Busqueda {
	
	Configuracion cfg;
	Problema nuevoProblema;
	
	Casilla lainicial, lafinal;
	public Busqueda (Problema p){
		cfg = new Configuracion();
		nuevoProblema = new Problema(cfg);
		
		lainicial = nuevoProblema.getIniciale();
		lafinal = nuevoProblema.getFinale();
	}
	
	abstract public boolean ejecutar();
	
	public List<Accion> getCamino (){
	
		return null;
	}	
	
	public String getName() {
		return null;
	}
		
	public List <Casilla> getAbierto(){
		return null;
	}
	
	public HashSet	<Casilla> getCerrados() {
		return  null;
	}
	
	public int getNumNodosExtendidos() {
		return 0;
	}
	
	public int getNumMaxAbiertos() {
		return 0;
	}
	
	public int getCoste (){
		int gasto = 0;
		
		
		return gasto;
	}	
}