package com.wiss.backend.entity;

import com.wiss.backend.model.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id // <- Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <- PostgreSQL macht Auto-Increment
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "longitude", nullable = false, length = 50)
    private Double longitude;

    @Column(name = "latitude", nullable = false, length = 50)
    private Double latitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private EventStatus status;

    // Default Konstruktor
    public Event() {}

    // Konstruktor ohne ID
    public Event(String title, LocalDate date, String category, Double longitude, Double latitude, EventStatus status) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    // Konstruktor mit ID
    public Event(Long id, String title, LocalDate date, String category, Double longitude, Double latitude, EventStatus status) {
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

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

}
