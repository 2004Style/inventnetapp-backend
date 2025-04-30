package com.chavezsuarez.errors;

import java.net.URI;
// import java.time.LocalDateTime;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        String developerName = "Ruben chavez";
        String developerPage = "https://convertsystems.store";
        String appName = "Evaluacion soluciones web 2025";

        String requestPath = request.getDescription(false).replace("uri=", "");
        
        String fullUrl = "http://localhost:8080" + requestPath;

        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Recurso no encontrado")
                .type(URI.create(fullUrl))
                .detail("No se encontró el recurso solicitado en verifique que el ID o parámetros sean correctos.")
                .property("application", appName)
                .property("developer", developerName)
                .property("developer-page", developerPage)
                .property("timestamp", LocalDateTime.now().toString())
                .build();
    }

}
