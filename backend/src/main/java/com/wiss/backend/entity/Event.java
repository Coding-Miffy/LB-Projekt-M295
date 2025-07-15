package com.wiss.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id // <- Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <- PostgreSQL macht Auto-Increment
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "longitude", length = 50)
    private Double longitude;

    @Column(name = "latitude", length = 50)
    private Double latitude;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    // Default Konstruktor
    public Event() {}

    // Konstruktor ohne ID
    public Event(String title, String category, Double longitude, Double latitude, String status) {
        this.title = title;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    // Konstruktor mit ID
    public Event(Long id, String title, String category, Double longitude, Double latitude, String status) {
        this.id = id;
        this.title = title;
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
