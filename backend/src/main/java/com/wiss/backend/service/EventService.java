package com.wiss.backend.service;

import com.wiss.backend.entity.Event;
import com.wiss.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    // Konstruktor für Dependency Injection
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Alle Events abrufen
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Ein spezifisches Event finden
    public Event getEventById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new RuntimeException("Event with id " + id + " not found");
        }

        return event;
    }

    // Events nach Kategorien filtern
        // ToDo

    // Events nach Status filtern
    public List<Event> getEventsbyStatus(String status) {
        validateStatus(status);
        return eventRepository.findByStatus(status.toLowerCase());
    }

    // Anzahl aller Events
    public int getTotalEventsCount() {
        return eventRepository.count();
    }


    // ToDo: Private Hilfsmethoden für die Validierung der Kategorien


    // Validierung: Status
    private void validateStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        List<String> validStatus = List.of("open", "closed");
        if (!validStatus.contains(status.toLowerCase())) {
            throw new IllegalArgumentException("Status " + status + " is not valid. Valid statuses are: " + validStatus);
        }
    }

}
