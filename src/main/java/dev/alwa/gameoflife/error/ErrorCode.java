package dev.alwa.gameoflife.error;

import org.springframework.http.HttpStatusCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum ErrorCode {
    INVALID_GRID_DIMENSIONS(BAD_REQUEST, "All the rows must be of the same length and have three elements at least");

    private final HttpStatusCode statusCode;
    private final String message;

    ErrorCode(HttpStatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
