package br.com.edicursos.plataformaapi.plataformaapi;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private StandardError err = new StandardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        this.err.setTimestamp(Instant.now());
        this.err.setStatus(status.value());
        this.err.setError("Entity not found");
        this.err.setMessage(e.getMessage());
        this.err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.err);
    }

}
