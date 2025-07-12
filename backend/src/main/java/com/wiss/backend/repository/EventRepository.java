package com.wiss.backend.repository;

import com.wiss.backend.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    private List<Event> events;

    // Konstruktor
    public EventRepository() {

        initializeEvents();
    }

    // Hilfsmethode - Mockt Events
    private void initializeEvents() {
        events = new ArrayList<>();

        events.add(new Event(
                1L,
                "Wildfire in Zurich",
                "wildfires",
                8.55,
                47.37,
                "open"
        ));

        events.add(new Event(
                2L,
                "Flood in Manhattan",
                "floods",
                -73.99,
                40.73,
                "closed"
        ));

        events.add(new Event(
                3L,
                "Volcano in Naples",
                "volcanoes",
                14.48,
                40.85,
                "open"
        ));

        events.add(new Event(
                4L,
                "Iceberg in Antarctica",
                "seaLakeIce",
                -60.0,
                -82.5,
                "closed"
        ));
    }

    // Alle Events zurückgeben
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    // Einen spezifischen Event anhand der ID finden
    public Event findById(Long id) {
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Events nach Kategorie filtern
    public List<Event> findByCategory(String category) {
        return events.stream()
                .filter(e -> e.getCategory().equals(category))
                .toList();
    }

    // Events nach Status filtern
    public List<Event> findByStatus(String status) {
        return events.stream()
                .filter(e -> e.getStatus().equals(status))
                .toList();
    }

    // Anzahl aller Events
    public int count() {
        return events.size();
    }

}
