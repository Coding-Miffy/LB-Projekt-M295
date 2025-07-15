package com.wiss.backend.exception;

/**
 * Exception für ungültige Event-Status.
 */
public class StatusNotFoundException extends RuntimeException{

    private final String status;

    /**
     * Erstellt eine neue StatusNotFoundException.
     *
     * @param status Status, der nicht gefunden werden konnte.
     */
    public StatusNotFoundException(String status) {
        super("Status " + status + " not found");
        this.status = status;
    }

    /**
     * @return Status, der nicht gefunden werden konnte.
     */
    public String getStatus() {
        return status;
    }
}
