package com.wiss.backend.controller;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.dto.EventFormDTO;
import com.wiss.backend.entity.Event;
import com.wiss.backend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "CRUD-Operations für die EONET-Events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /api/events - Alle Events abrufen
    @GetMapping
    @Operation(
            summary = "Alle Events abrufen",
            description = "Gibt alle verfügbaren Events zurück"
    )
    @ApiResponse(responseCode = "200", description = "Events erfolgreich abgerufen")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEventsAsDTO();
    }

    // GET /api/events/1 - Einen spezifischen Event abrufen
    @GetMapping("/{id}")
    @Operation(
            summary = "Event nach ID abrufen",
            description = "Gibt ein spezifisches Event anhand der ID zurück"
    )
    @ApiResponse(responseCode = "200", description = "Event gefunden")
    @ApiResponse(responseCode = "404", description = "Event nicht gefunden")
    public EventDTO getEventById(
            @Parameter(description = "ID des Events", example = "1", required = true)
            @PathVariable Long id) {
        return eventService.getEventByIdAsDTO(id);
    }

    // GET /api/events/category/floods - Events nach Kategorie
    @GetMapping("/categories/{category}")
    @Operation(
            summary = "Events nach Kategorien abrufen",
            description = "Gibt alle Events einer Kategorie zurück"
    )
    @ApiResponse(responseCode = "200", description = "Events erfolgreich abgerufen")
    @ApiResponse(responseCode = "400", description = "Ungültige Kategorie übergeben")
    public List<EventDTO> getEventsByCategory(
            @Parameter(description = "Kategorie", example = "wildfires", required = true)
            @PathVariable String category) {
        return eventService.getEventsByCategoryAsDTO(category);
    }

    // GET /api/events/status/open - Events nach Status
    @GetMapping("/status/{status}")
    @Operation(
            summary = "Events nach Status abrufen",
            description = "Gibt alle Events eines Status zurück"
    )
    @ApiResponse(responseCode = "200", description = "Events erfolgreich abgerufen")
    @ApiResponse(responseCode = "400", description = "Ungültiger Status übergeben")
    public List<EventDTO> getEventsByStatus(
            @Parameter(description = "Status", example = "open", required = true)
            @PathVariable String status) {
        return eventService.getEventsByStatusAsDTO(status);
    }

    // GET /api/events/2025-07-11
    @GetMapping("/date/{date}")
    public List<EventDTO> getEventsByDate(
            @PathVariable LocalDate date) {
        return eventService.getEventsByDateAsDTO(date);
    }

    // GET /api/events/count - Anzahl aller Events
    @GetMapping("/count")
    @Operation(
            summary = "Anzahl aller Events abrufen",
            description = "Gibt die Anzahl aller Events zurück"
    )
    @ApiResponse(responseCode = "200", description = "Anzahl Events erfolgreich abgerufen")
    public long getEventCount() {
        return eventService.getTotalEventsCount();
    }

    // POST /api/events - Neues Event erstellen
    @PostMapping
    @Operation(
            summary = "Neues Event erstellen",
            description = "Erstellt ein neues EONET-Event"
    )
    @ApiResponse(responseCode = "200", description = "Event erfolgreich erstellt")
    @ApiResponse(responseCode = "400", description = "Ungültige Daten übergeben")
    @ApiResponse(responseCode = "500", description = "Unvollständige Daten übergeben")
    public EventDTO createEvent(
            @Valid @RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    // PUT /api/events/2 - Event aktualisieren
    @PutMapping("/{id}")
    @Operation(
            summary = "Event aktualisieren",
            description = "Aktualisiert ein bestehendes Event"
    )
    @ApiResponse(responseCode = "200", description = "Event erfolgreich aktualisiert")
    @ApiResponse(responseCode = "400", description = "Ungültige Daten übergeben")
    @ApiResponse(responseCode = "404", description = "Event nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Unvollständige Daten übergeben")
    public EventDTO updateEvent(
            @Parameter(description = "ID des Events", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    // DELETE /api/events/5 - Event löschen
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Event löschen",
            description = "Löscht ein bestehendes Event"
    )
    @ApiResponse(responseCode = "200", description = "Event erfolgreich gelöscht")
    @ApiResponse(responseCode = "404", description = "Event nicht gefunden")
    public void deleteEvent(
            @Parameter(description = "ID des Events", example = "1", required = true)
            @PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    // Kombinierte Filter
    @GetMapping("/filter")
    @Operation(
            summary = "Events filtern",
            description = "Filtert bestehende Events"
    )
    @ApiResponse(responseCode = "200", description = "Events erfolgreich gefiltert")
    @ApiResponse(responseCode = "400", description = "Ungültige Daten übergeben")
    public List<EventDTO> getEventsByFilter(
            @Parameter(description = "Kategorie", example = "wildfires")
            @RequestParam(required = false) String category,
            @Parameter(description = "Status", example = "open")
            @RequestParam(required = false) String status,
            @Parameter(description = "Startdatum", example = "2025-07-01")
            @RequestParam(required = false) LocalDate start,
            @Parameter(description = "Enddatum", example = "2025-07-15")
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
    @Operation(
            summary = "Anzahl aller Events einer Kategorie abrufen",
            description = "Gibt die Anzahl aller Events einer Kategorie zurück"
    )
    @ApiResponse(responseCode = "200", description = "Anzahl Events erfolgreich abgerufen")
    @ApiResponse(responseCode = "400", description = "Ungültige Kategorie übergeben")
    public long getEventCountByCategory(
            @Parameter(description = "Kategorie", example = "wildfires", required = true)
            @PathVariable String category) {
        return eventService.getTotalEventsByCategory(category);
    }

    @GetMapping("/stats/status/{status}")
    @Operation(
            summary = "Anzahl aller Events eines Status abrufen",
            description = "Gibt die Anzahl aller Events eines Status zurück"
    )
    @ApiResponse(responseCode = "200", description = "Anzahl Events erfolgreich abgerufen")
    @ApiResponse(responseCode = "400", description = "Ungültige Kategorie übergeben")
    public long getEventCountByStatus(
            @Parameter(description = "Status", example = "open", required = true)
            @PathVariable String status) {
        return eventService.getTotalEventsByStatus(status);
    }

    @GetMapping("/stats/date/{start}/{end}")
    @Operation(
            summary = "Anzahl aller Events eines Zeitraums abrufen",
            description = "Gibt die Anzahl aller Events innerhalb eines Zeitraums zurück"
    )
    @ApiResponse(responseCode = "200", description = "Anzahl Events erfolgreich abgerufen")
    @ApiResponse(responseCode = "500", description = "Ungültiges Datum übergeben")
    public long getEventCountByDateBetween(
            @Parameter(description = "Startdatum", example = "2025-07-01", required = true)
            @PathVariable LocalDate start,
            @Parameter(description = "Enddatum", example = "2025-07-15", required = true)
            @PathVariable LocalDate end) {
        return eventService.getTotalEventsByDateBetween(start, end);
    }

    // Frontend Endpoints
    @GetMapping("/all")
    @Operation(
            summary = "Alle Events abrufen",
            description = "Gibt alle verfügbaren Events zurück"
    )
    @ApiResponse(responseCode = "200", description = "Events erfolgreich abgerufen")
    public List<EventFormDTO> getAllFormEvents() {
        return eventService.getAllEventsAsFormDTO();
    }

    @GetMapping("/{id}/edit")
    @Operation(
            summary = "Event nach ID abrufen über Formular",
            description = "Gibt ein spezifisches Event anhand der ID zurück, optimiert für das Frontend-Formular"
    )
    @ApiResponse(responseCode = "200", description = "Event gefunden")
    @ApiResponse(responseCode = "404", description = "Event nicht gefunden")
    public EventFormDTO getEventByIdForEdit(
            @Parameter(description = "ID des Events", example = "1", required = true)
            @PathVariable Long id) {
        return eventService.getEventByIdAsFormDTO(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Neues Event erstellen über Formular",
            description = "Erstellt ein neues EONET-Event über das Frontend-Formular"
    )
    @ApiResponse(responseCode = "201", description = "Event erfolgreich erstellt")
    @ApiResponse(responseCode = "400", description = "Ungültige Daten übergeben")
    @ApiResponse(responseCode = "500", description = "Unvollständige Daten übergeben")
    public EventFormDTO createEventFromForm(
            @Parameter(description = "Event-Daten", required = true)
            @Valid @RequestBody Event event) {
        return eventService.createEventFromForm(event);
    }

    @PutMapping("/{id}/update")
    @Operation(
            summary = "Event aktualisieren über Formular",
            description = "Aktualisiert ein bestehendes Event über das Frontend-Formular"
    )
    @ApiResponse(responseCode = "200", description = "Event erfolgreich aktualisiert")
    @ApiResponse(responseCode = "400", description = "Ungültige Daten übergeben")
    @ApiResponse(responseCode = "404", description = "Event nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Unvollständige Daten übergeben")
    public EventFormDTO updateEventFromForm(
            @Parameter(description = "ID des Events", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Event-Daten", required = true)
            @Valid @RequestBody Event event) {
        return eventService.updateEventFromForm(id, event);
    }

}
