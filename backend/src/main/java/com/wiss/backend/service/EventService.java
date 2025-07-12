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
        // ToDo

    // Anzahl aller Events
    public int getTotalEventsCount() {
        return eventRepository.count();
    }


    // ToDo: Private Hilfsmethoden für die Validierung der Kategorien und Stati

}
