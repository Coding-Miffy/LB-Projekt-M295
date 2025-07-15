package com.wiss.backend.exception;

/**
 * Exception für ungültige oder unvollständige Event-Daten.
 *
 * Diese Exception wird geworfen, wenn Business-Logik-Regeln verletzt werden,
 * die nicht bereits durch Bean Validation abgedeckt sind.
 */
public class InvalidEventDataException extends RuntimeException {

    /**
     * Erstellt eine neue InvalidEventDataException.
     *
     * @param message Beschreibung des Validierungsfehlers.
     */
    public InvalidEventDataException(String message) {
        super(message);
    }
}
