package ar.com.seminario.corralon.exceptions;

public class MaderasNoEncontradasException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -577061597522802083L;
	
	private String message = "No se encontraron maderas segun los parametros especificados";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
