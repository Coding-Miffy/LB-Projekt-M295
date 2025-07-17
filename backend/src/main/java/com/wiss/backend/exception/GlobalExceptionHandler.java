package com.wiss.backend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wiss.backend.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Globaler Exception-Handler, der alle unbehandelten Exceptions abfängt
 * und in strukturierte Error-Responses konvertiert.
 *
 * @ControllerAdvice macht diese Klasse zu einem globalen Exception-Interceptor.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Behandelt EventNotFoundException und gibt 404 zurück.
     *
     * @param ex Die gefangene Exception
     * @param request Der ursprüngliche HTTP-Request
     * @return 404-Response mit strukturierter Fehlermeldung
     */
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventNotFound(EventNotFoundException ex, WebRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "EVENT_NOT_FOUND",
                "Event mit ID " + ex.getEventId() + " wurde nicht gefunden",
                404,
                LocalDateTime.now(),
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FutureDateException.class)
    public ResponseEntity<ErrorResponseDTO> handleFutureDate(FutureDateException ex, WebRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_DATE",
                "Das Datum '" + ex.getDate() + "' liegt in der Zukunft und ist nicht erlaubt.",
                400,
                LocalDateTime.now(),
                extractPath(request)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoordinateOutOfRangeException.class)
    public ResponseEntity<ErrorResponseDTO> handleCoordinateOutOfRange(CoordinateOutOfRangeException ex, WebRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_COORDINATE",
                "Ungültiger Wert für " + ex.getCoordinateType() + ": " + ex.getValue() +
                        ". Erlaubte Werte: " +
                        (ex.getCoordinateType().equals("latitude") ? "-90 bis 90" : "-180 bis 180"),
                400,
                LocalDateTime.now(),
                extractPath(request)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Behandelt InvalidEventDataException und gibt 400 zurück.
     */
    @ExceptionHandler(InvalidEventDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidEventData(InvalidEventDataException ex, WebRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_EVENT_DATA",
                ex.getMessage(),
                400,
                LocalDateTime.now(),
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Behandelt IllegalArgumentException und gibt 400 zurück.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_INPUT",
                ex.getMessage(),
                400,
                LocalDateTime.now(),
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Jackson-Exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        // Überprüfen, ob die Ursache eine InvalidFormatException ist (Enum-Fehler)
        if (ex.getCause() instanceof InvalidFormatException cause) {
            String fieldName = cause.getPath().get(0).getFieldName();
            String invalidValue = cause.getValue().toString();

            if ("category".equals(fieldName)) {
                ErrorResponseDTO error = new ErrorResponseDTO(
                        "INVALID_CATEGORY",
                        "Kategorie '" + invalidValue + "' ist nicht gültig. " +
                                "Gültige Kategorien: drought, dustHaze, earthquakes, floods, landslides, manmade, seaLakeIce, severeStorms, snow, volcanoes, waterColor, wildfires",
                        400,
                        LocalDateTime.now(),
                        extractPath(request)
                );
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            if ("status".equals(fieldName)) {
                ErrorResponseDTO error = new ErrorResponseDTO(
                        "INVALID_STATUS",
                        "Status '" + invalidValue + "' ist nicht gültig. Erlaubt sind: open, closed.",
                        400,
                        LocalDateTime.now(),
                        extractPath(request)
                );
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        // Fallback für andere Fälle
        ErrorResponseDTO error = new ErrorResponseDTO(
                "MALFORMED_JSON",
                "Die Anfrage konnte nicht gelesen werden: " + ex.getMessage(),
                400,
                LocalDateTime.now(),
                extractPath(request)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Fallback für alle anderen Exceptions - gibt 500 zurück.
     *
     * Wichtig: Keine technischen Details preisgeben!
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex, WebRequest request) {
        // Log the actual exception for debugging (nicht an Frontend senden!)
        System.err.println("Unhandled exception: " + ex.getMessage());
        ex.printStackTrace();

        ErrorResponseDTO error = new ErrorResponseDTO(
                "INTERNAL_SERVER_ERROR",
                "Ein unerwarteter Fehler ist aufgetreten. Bitte versuchen Sie es später erneut.",
                500,
                LocalDateTime.now(),
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Extrahiert den API-Pfad aus dem WebRequest.
     */
    private String extractPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    /**
     * Behandelt Bean Validation Errors von @Valid Annotations.
     *
     * @param ex Die Validation-Exception mit Details zu allen fehlgeschlagenen Validierungen
     * @param request Der ursprüngliche HTTP-Request
     * @return 400 Response mit detaillierten Validierungsfehlern
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        // Sammle alle Validierungsfehler
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Erstelle benutzerfreundliche Fehlermeldung
        StringBuilder message = new StringBuilder("Validation exception: ");
        errors.forEach((field, error) -> message.append(field)
                .append(" - ").append(error).append("; "));

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "VALIDATION_ERROR",
                message.toString(),
                400,
                LocalDateTime.now(),
                extractPath(request)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
