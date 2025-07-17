package com.wiss.backend.dto;

import com.wiss.backend.controller.EventController;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "DTO für Frontend-Formulare (Create/Update)")
public class EventFormDTO {

    @Schema(description = "Eindeutige ID des Events", example = "1")
    private Long id;

    @NotBlank(message = "Titel ist erforderlich")
    @Size(min = 5, max = 255, message = "Titel muss zwischen 5 und 255 Zeichen lang sein")
    @Schema(description = "Titel des Events", example = "Flood in Jakarta")
    private String title;

    @NotBlank(message = "Datum ist erforderlich")
    @PastOrPresent(message = "Datum darf nicht in der Zukunft liegen")
    @Schema(description = "Datum des Events", example = "2025-07-01")
    private LocalDate date;

    @NotNull(message = "Kategorie ist erforderlich")
    @Schema(description = "Kategorie des Events", example = "floods")
    private EventCategory category;

    @NotNull(message = "Längengrad ist erforderlich")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Längengrad muss >= -180.0 sein")
    @DecimalMax(value = "180.0", inclusive = true, message = "Längengrad muss <= 180.0 sein")
    @Schema(description = "Längengrad des Event-Standorts", example = "106.85")
    private Double longitude;

    @NotNull(message = "Breitengrad ist erforderlich")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Breitengrad muss >= -90.0 sein")
    @DecimalMax(value = "90.0", inclusive = true, message = "Breitengrad muss <= 90.0 sein")
    @Schema(description = "Breitengrad des Event-Standorts", example = "-6.21")
    private Double latitude;

    @NotNull(message = "Status ist erforderlich")
    @Schema(description = "Status des Events", example = "open")
    private EventStatus status;

    // Standard-Konstruktor für JSON-Deserialisierung.
    public EventFormDTO() {}

    // Vollständiger Konstruktor für das Erstellen eines EventFormDTO.
    public EventFormDTO(String title, LocalDate date, EventCategory category, Double longitude, Double latitude, EventStatus status) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    // Vollständiger Konstruktor für das Bearbeiten eines EventFormDTO.
    public EventFormDTO(Long id, String title, LocalDate date, EventCategory category, Double longitude, Double latitude, EventStatus status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    // Konvertiert das EventFormDTO zu einem EventDTO für die Frontend-Anzeige.
    public EventDTO toEventDTO(Long id) {
        return new EventDTO(
                id,
                this.title,
                this.date,
                this.category,
                this.longitude,
                this.latitude,
                this.status
        );
    }

    // Erstellt ein EventFormDTO aus einem bestehenden EventDTO
    public static EventFormDTO fromEventDTO(EventDTO eventDTO) {
        return new EventFormDTO(
                eventDTO.getTitle(),
                eventDTO.getDate(),
                eventDTO.getCategory(),
                eventDTO.getLongitude(),
                eventDTO.getLatitude(),
                eventDTO.getStatus()
        );
    }

    // Erstellt eine String-Repräsentation des DTOs.
    @Override
    public String toString() {
        return "EventFormDTO{" +
                "title='" + title + "'" +
                ", date='" + date + "'" +
                ", category='" + category + "'" +
                ", longitude='" + longitude + "'" +
                ", latitude='" + latitude + "'" +
                ", status='" + status + "'" +
                "}";
    }
}