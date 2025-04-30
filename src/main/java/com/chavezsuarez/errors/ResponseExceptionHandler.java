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
        String errorCode = "ERR-WEB-7385";

        String requestPath = request.getDescription(false).replace("uri=", "");
        
        String fullUrl = "http://localhost:8080" + requestPath;

        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Recurso no encontrado")
                .type(URI.create(fullUrl))
                .detail(String.format(
                        "No se encontró el recurso solicitado en: %s. Verifique que el ID o parámetros sean correctos.",
                        fullUrl))
                .property("application", appName)
                .property("developer", developerName)
                .property("developer-page", developerPage)
                .property("error-code", errorCode)
                .property("timestamp", LocalDateTime.now().toString())
                .property("request-url", fullUrl)
                .property("endpoint-path", requestPath)
                .property("support-email", developerPage + "/contact")
                .property("documentation-url", "https://docs.miaplicacion.com/errors/ERR-WEB-7385")
                .property("troubleshooting",
                        "1. Verifique que el ID exista\n2. Confirme los permisos de acceso\n3. Revise los logs de la aplicación")
                .build();
    }

}
