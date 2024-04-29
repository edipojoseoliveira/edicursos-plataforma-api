package br.com.edicursos.plataformaapi.plataformaapi.controller.exception;

public class ControllerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -419312822371017335L;

	public ControllerNotFoundException(String message) {
        super(message);
    }

}
