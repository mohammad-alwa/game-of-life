package dev.alwa.gameoflife.config;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static java.util.stream.Collectors.joining;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(errorCode.getStatusCode(), errorCode.getMessage());
        problemDetail.setType(ServletUriComponentsBuilder.fromCurrentContextPath()
                .pathSegment("error", errorCode.name().toLowerCase().replace('_', '-'))
                .build().toUri());
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> "'%s' %s".formatted(f.getField(), f.getDefaultMessage()))
                .collect(joining(", "));
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setType(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("error/bad-request").build().toUri());
        problemDetail.setInstance(ex.getBody().getInstance());
        return ResponseEntity.status(status).body(problemDetail);
    }
}
