package br.com.edicursos.plataformaapi.plataformaapi.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(status.value());
        validateError.setError("Erro de validação");
        validateError.setMessage(e.getMessage());
        validateError.setPath(request.getRequestURI());
        
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
			validateError.addMensagem(f.getField(), f.getDefaultMessage());
		}
        
        return ResponseEntity.status(status).body(validateError);
    }

}
