package com.wiss.backend.entity;

public class Event {

    // Felder
    private Long id;
    private String title;
    private String category;
    private Double longitude;
    private Double latitude;
    private String status;

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
