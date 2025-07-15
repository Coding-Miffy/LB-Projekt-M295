package com.wiss.backend.exception;

/**
 * Diese Exception wird geworfen, wenn ein Event mit der
 * angegebenen ID nicht gefunden werden konnte.
 *
 * Diese Exception sollte zu einem HTTP 404 Status-Code führen.
 */
public class EventNotFoundException extends RuntimeException {

    private final Long eventId;

    /**
     * Erstellt eine neue EventNotFoundException.
     *
     * @param eventId Die ID des Events, dass nicht gefunden werden konnte.
     */
    public EventNotFoundException(Long eventId) {
        super("Event with ID " + eventId + " not found");
        this.eventId = eventId;
    }

    /**
     * @return Die ID des Events, dass nicht gefunden werden konnte.
     */
    public Long getEventId() {
        return eventId;
    }
}
