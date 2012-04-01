package ar.com.seminario.corralon.exceptions;

public class PresupuestoSinItemsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996375662491420374L;

	private String errorMessage = "No puede guardar un presupuesto que no tenga ningun item";

	public String getErrorMessage() {
		return errorMessage;
	}
}
