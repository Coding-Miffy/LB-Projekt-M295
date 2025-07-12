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
    // ToDo: categories und geometry
    private String status;

    // Konstruktor ohne ID
    public Event(String title, String status) {
        this.title = title;
        this.status = status;
        // ToDo: categories und geometry
    }

    // Konstruktor mit ID
    public Event(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
        // ToDo: categories und geometry
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ToDo: categories und geometry
}
