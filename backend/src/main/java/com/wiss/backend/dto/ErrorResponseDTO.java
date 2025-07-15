package com.wiss.backend.dto;

import java.time.LocalDateTime;

/**
 * Standardisierte Error-Response für alle API-Felder.
 *
 * Bietet konsistente Struktur für Frontend Error-Handling.
 */
public class ErrorResponseDTO {

    private String error;
    private String message;
    private int code;
    private LocalDateTime timestamp;
    private String path;

    /**
     * Erstellt eine neue Error-Response.
     *
     * @param error Error-Code (z. B. "EVENT_NOT_FOUND")
     * @param message Benutzerfreundliche Fehlermeldung
     * @param code HTTP Status-Code
     * @param path Der aufgerufene API-Pfad
     */

    public ErrorResponseDTO(String error, String message, int code, LocalDateTime timestamp, String path) {
        this.error = error;
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.path = path;
    }

    // Getter & Setter

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
