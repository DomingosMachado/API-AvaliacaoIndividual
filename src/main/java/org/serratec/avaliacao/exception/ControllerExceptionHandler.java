package org.serratec.avaliacao.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @org.springframework.lang.NonNull MethodArgumentNotValidException ex,
            @org.springframework.lang.NonNull HttpHeaders headers,
            @org.springframework.lang.NonNull HttpStatusCode status,
            @org.springframework.lang.NonNull WebRequest request) {


        List<String> erros = new ArrayList<>();
        for (FieldError err: ex.getBindingResult().getFieldErrors()){
            erros.add(err.getField() + ": " + err.getDefaultMessage());
        }
                
        ErroResposta erroResposta = new ErroResposta(status.value(),
            "Existem campos inválidos, confira o preenchimento!", LocalDateTime.now(), erros);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }
}