package ar.com.seminario.corralon.exceptions;

public class PresupuestosNoEncontradosException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 147090765672317072L;

	private String errorMessage = "No se encontraron presupuestos segun los parametros especificados";

	public String getErrorMessage() {
		return errorMessage;
	}
}
