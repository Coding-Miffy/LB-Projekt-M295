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
                "open"
        ));

        events.add(new Event(
                2L,
                "Flood in Manhattan",
                "closed"
        ));

        events.add(new Event(
                3L,
                "Volcano in Naples",
                "open"
        ));

        events.add(new Event(
                4L,
                "Iceberg in Antarctica",
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
        // ToDo

    // Events nach Status filtern
        // ToDo

    // Anzahl aller Events
    public int count() {
        return events.size();
    }

}
