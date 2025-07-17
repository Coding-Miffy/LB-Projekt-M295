package com.wiss.backend.exception;

import java.time.LocalDate;

/**
 * Wird geworfen, wenn ein Event-Datum in der Zukunft liegt.
 */
public class FutureDateException extends RuntimeException {

    private final LocalDate date;

    /**
     * Erstellt eine neue FutureDateException.
     *
     * @param date Datum, das in der Zukunft liegt.
     */
    public FutureDateException(LocalDate date) {
        super("Date " + date + " cannot be in the future");
        this.date = date;
    }

    /**
     * @return Das ungültige Datum.
     */
    public LocalDate getDate() {
        return date;
    }
}
