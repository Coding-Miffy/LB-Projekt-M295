package com.wiss.backend.controller;

import com.wiss.backend.entity.Event;
import com.wiss.backend.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /api/events - Alle Events abrufen
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // GET /api/events/1 - Einen spezifischen Event abrufen
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // GET /api/events/category/wildfires - Events nach Kategorie
    @GetMapping("/categories/{category}")
    public List<Event> getEventsByCategory(@PathVariable String category) {
        return eventService.getEventsByCategory(category);
    }

    // GET /api/events/status/open - Events nach Status
    @GetMapping("/status/{status}")
    public List<Event> getEventsByStatus(@PathVariable String status) {
        return eventService.getEventsbyStatus(status);
    }

    // GET /api/events/count - Anzahl aller Events
    @GetMapping("/count")
    public int getEventCount() {
        return eventService.getTotalEventsCount();
    }
}
