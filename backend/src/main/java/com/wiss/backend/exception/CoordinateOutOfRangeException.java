package com.wiss.backend.exception;

/**
 * Wird geworfen, wenn eine Koordinate (Longitude oder Latitude)
 * ausserhalb des gültigen Wertebereichs liegt.
 */
public class CoordinateOutOfRangeException extends RuntimeException{

    private final String coordinateType;
    private final double value;

    /**
     * Erstellt eine neue CoordinateOutOfRangeException.
     *
     * @param coordinateType "longitude" oder "latitude"
     * @param value Der ungültige Wert
     */
    public CoordinateOutOfRangeException(String coordinateType, double value) {
        super("Invalid " + coordinateType + ": " + value + " is out of valid range");
        this.coordinateType = coordinateType;
        this.value = value;
    }

    /**
     * @return Der Typ der Koordinate ("longitude" oder "latitude").
     */
    public String getCoordinateType() {
        return coordinateType;
    }

    /**
     * @return Der ungültige Wert.
     */
    public double getValue() {
        return value;
    }
}
