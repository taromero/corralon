package ar.com.seminario.corralon.exceptions;

public class UsuarioInexistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5726927084497253902L;
	
	private String errorMessage = "Usuario inexistente";

	public String getErrorMessage() {
		return errorMessage;
	}

}
