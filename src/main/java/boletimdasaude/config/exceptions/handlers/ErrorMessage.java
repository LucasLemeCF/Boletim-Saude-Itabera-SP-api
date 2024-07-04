package boletimdasaude.config.exceptions.handlers;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {

    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime thrownAt;

    public ErrorMessage(HttpStatus httpStatus, String message, LocalDateTime thrownAt) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.thrownAt = thrownAt;
    }

    public ErrorMessage() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getThrownAt() {
        return thrownAt;
    }

    public void setThrownAt(LocalDateTime thrownAt) {
        this.thrownAt = thrownAt;
    }
}
