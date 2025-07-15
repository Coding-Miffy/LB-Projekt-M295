package com.wiss.backend.dto;

import java.time.LocalDate;

public class EventDTO {

    private Long id;
    private String title;
    private LocalDate date;
    private String category;
    private Double longitude;
    private Double latitude;
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
