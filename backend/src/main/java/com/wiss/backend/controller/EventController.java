package com.wiss.backend.controller;

import com.wiss.backend.dto.EventDTO;
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
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEventsAsDTO();
    }

    // GET /api/events/1 - Einen spezifischen Event abrufen
    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventByIdAsDTO(id);
    }

    // GET /api/events/category/wildfires - Events nach Kategorie
    @GetMapping("/categories/{category}")
    public List<EventDTO> getEventsByCategory(@PathVariable String category) {
        return eventService.getEventsByCategoryAsDTO(category);
    }

    // GET /api/events/status/open - Events nach Status
    @GetMapping("/status/{status}")
    public List<EventDTO> getEventsByStatus(@PathVariable String status) {
        return eventService.getEventsbyStatusAsDTO(status);
    }

    // GET /api/events/count - Anzahl aller Events
    @GetMapping("/count")
    public int getEventCount() {
        return eventService.getTotalEventsCount();
    }
}
