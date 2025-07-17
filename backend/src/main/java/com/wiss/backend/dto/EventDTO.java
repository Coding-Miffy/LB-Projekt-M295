package com.wiss.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "DTO für Events")
public class EventDTO {

    @Schema(description = "Eindeutige ID des Events", example = "1")
    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    @Size(min = 5, max = 255, message = "Titel muss zwischen 5 und 255 Zeichen lang sein")
    @Schema(description = "Titel des Events", example = "Flood in Jakarta")
    private String title;

    @NotBlank(message = "Datum darf nicht leer sein")
    @PastOrPresent(message = "Datum darf nicht in der Zukunft liegen")
    @Schema(description = "Datum des Events", example = "2025-07-01")
    private LocalDate date;

    @NotBlank(message = "Kategorie darf nicht leer sein")
    @Pattern(regexp = "wildfires|severeStorms|volcanoes|seaLakeIce|earthquakes|floods|landslides|snow|drought|dustHaze|manmade|waterColor",
            message = "Ungültige Kategorie. Erlaubt sind: wildfires, severeStorms, volcanoes, seaLakeIce, earthquakes, floods, landslides, snow, drought, dustHaze, manmade, waterColor")
    @Schema(description = "Kategorie des Events", example = "floods")
    private String category;

    @NotNull(message = "Längengrad darf nicht null sein")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Längengrad muss >= -180.0 sein")
    @DecimalMax(value = "180.0", inclusive = true, message = "Längengrad muss <= 180.0 sein")
    @Schema(description = "Längengrad des Event-Standorts", example = "106.85")
    private Double longitude;

    @NotNull(message = "Breitengrad darf nicht null sein")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Breitengrad muss >= -90.0 sein")
    @DecimalMax(value = "90.0", inclusive = true, message = "Breitengrad muss <= 90.0 sein")
    @Schema(description = "Breitengrad des Event-Standorts", example = "-6.21")
    private Double latitude;

    @NotBlank(message = "Status darf nicht leer sein")
    @Pattern(regexp = "open|closed", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Status muss 'open' oder 'closed' sein")
    @Schema(description = "Status des Events", example = "open")
    private String status;

    // Default Konstruktor (für JSON Deserialisierung)
    public EventDTO() {}

    // Vollständiger Konstruktor
    public EventDTO(Long id, String title, LocalDate date, String category, Double longitude, Double latitude, String status) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
