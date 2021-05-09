package Main;

public class Casilla {
	public int _fila, _columna;
	private boolean _esObstaculo = false;
	private boolean pinicial = false;
	private boolean pfinal = false;
		
	public Casilla (int filaP, int columnaP){
		_fila 		= filaP;
		_columna 	= columnaP;
	}

	public boolean is_esObstaculo() {
		return _esObstaculo;
	}

	public void set_esObstaculo(boolean _esObstaculo) {
		this._esObstaculo = _esObstaculo;
	}

	public boolean isPinicial() {
		return pinicial;
	}

	public void setPinicial(boolean pinicial) {
		this.pinicial = pinicial;
	}

	public boolean isPfinal() {
		return pfinal;
	}

	public void setPfinal(boolean pfinal) {
		this.pfinal = pfinal;
	}
	
	
}
