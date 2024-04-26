package br.com.edicursos.plataformaapi.plataformaapi;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }

}
