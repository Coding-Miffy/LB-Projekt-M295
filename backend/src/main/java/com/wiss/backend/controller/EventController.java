package com.wiss.backend.controller;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // GET /api/events/category/floods - Events nach Kategorie
    @GetMapping("/categories/{category}")
    public List<EventDTO> getEventsByCategory(@PathVariable String category) {
        return eventService.getEventsByCategoryAsDTO(category);
    }

    // GET /api/events/status/open - Events nach Status
    @GetMapping("/status/{status}")
    public List<EventDTO> getEventsByStatus(@PathVariable String status) {
        return eventService.getEventsByStatusAsDTO(status);
    }

    // GET /api/events/2025-07-11
    @GetMapping("/date/{date}")
    public List<EventDTO> getEventsByDate(@PathVariable LocalDate date) {
        return eventService.getEventsByDateAsDTO(date);
    }

    // GET /api/events/count - Anzahl aller Events
    @GetMapping("/count")
    public long getEventCount() {
        return eventService.getTotalEventsCount();
    }

    // POST /api/events - Neues Event erstellen
    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    // PUT /api/events/2 - Event aktualisieren
    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    // DELETE /api/events/5 - Event löschen
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    // Kombinierte Filter
    @GetMapping("/filter")
    public List<EventDTO> getEventsByFilter(@RequestParam(required = false) String category,
                                            @RequestParam(required = false) String status,
                                            @RequestParam(required = false) LocalDate start,
                                            @RequestParam(required = false) LocalDate end) {
        if (category != null && status != null && start != null && end != null) {
            return eventService.getEventsByCategoryAndStatusAndDateBetween(category, status, start, end);
        } else if (category != null && start != null && end != null) {
            return eventService.getEventsByCategoryAndDateBetween(category, start, end);
        } else if (category != null && status != null) {
            return eventService.getEventsByCategoryAndStatus(category, status);
        } else if (status != null && start != null && end != null) {
            return eventService.getEventsByStatusAndDateBetween(status, start, end);
        } else if (start != null && end != null) {
            return eventService.getEventsByDateBetween(start, end);
        } else {
            return eventService.getAllEventsAsDTO();
        }
    }

    // Statistiken
    @GetMapping("/stats/categories/{category}")
    public long getEventCountByCategory(@PathVariable String category) {
        return eventService.getTotalEventsByCategory(category);
    }

    @GetMapping("/stats/status/{status}")
    public long getEventCountByStatus(@PathVariable String status) {
        return eventService.getTotalEventsByStatus(status);
    }

    @GetMapping("/stats/date/{start}/{end}")
    public long getEventCountByDateBetween(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return eventService.getTotalEventsByDateBetween(start, end);
    }
}
