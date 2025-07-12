package com.wiss.backend.entity;

import java.util.List;

public class Event {

    // {
    //  "events": [
    //    {
    //      "id": "EONET_1234",
    //      "title": "Wildfire in California",
    //      "categories": [{ "id": "wildfires", "title": "Wildfires" }],
    //      "geometry": [{ "coordinates": [-120.5, 36.2] }],
    //      "status": "open"
    //    }
    //  ]
    //}

    private Long id;
    private String title;
    private List<Category> categories;
    private List<Geometry> geometry;
    private String status;

    // Konstruktor ohne ID
    public Event(String title, List<Category> categories, String status, List<Geometry> geometry) {
        this.title = title;
        this.geometry = geometry;
        this.categories = categories;
        this.status = status;
    }

    // Konstruktor mit ID
    public Event(Long id, String title, List<Category> categories, String status, List<Geometry> geometry) {
        this.id = id;
        this.title = title;
        this.geometry = geometry;
        this.categories = categories;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Geometry> getGeometry() {
        return geometry;
    }

    public void setGeometry(List<Geometry> geometry) {
        this.geometry = geometry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
