package ar.com.seminario.corralon.exceptions;

public class PresupuestosNoEncontradosException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 147090765672317072L;

	private String message = "No se encontraron presupuestos segun los parametros especificados";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
