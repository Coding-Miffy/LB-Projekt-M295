package com.wiss.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class EventDTO {

    @Schema(description = "Eindeutige ID des Events", example = "1")
    private Long id;

    @Schema(description = "Titel des Events", example = "Flood in Jakarta")
    private String title;

    @Schema(description = "Datum des Events", example = "2025-07-01")
    private LocalDate date;

    @Schema(description = "Kategorie des Events", example = "floods")
    private String category;

    @Schema(description = "Längengrad des Event-Standorts", example = "106.85")
    private Double longitude;

    @Schema(description = "Breitengrad des Event-Standorts", example = "-6.21")
    private Double latitude;

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

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

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
