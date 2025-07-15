package com.wiss.backend.service;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.entity.Event;
import com.wiss.backend.mapper.EventMapper;
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

    // DTO-basierte Methoden
    // Alle Events abrufen
    public List<EventDTO> getAllEventsAsDTO() {
        List<Event> entities = eventRepository.findAll();
        return EventMapper.toDTOList(entities);
    }

    // Ein spezifisches Event finden
    public EventDTO getEventByIdAsDTO(Long id) {
        Event entity = getEventById(id);
        return EventMapper.toDTO(entity);
    }

    // Events nach Kategorien filtern
    public List<EventDTO> getEventsByCategoryAsDTO(String category) {
        List<Event> entities = eventRepository.findByCategory(category);
        return EventMapper.toDTOList(entities);
    }

    // Events nach Status filtern
    public List<EventDTO> getEventsbyStatusAsDTO(String status) {
        List<Event> entities = eventRepository.findByStatus(status);
        return EventMapper.toDTOList(entities);
    }


    // Weitere Methoden
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
    public List<Event> getEventsByCategory(String category) {
        validateCategory(category);
        return eventRepository.findByCategory(category.toLowerCase());
    }

    // Events nach Status filtern
    public List<Event> getEventsbyStatus(String status) {
        validateStatus(status);
        return eventRepository.findByStatus(status.toLowerCase());
    }

    // Anzahl aller Events
    public int getTotalEventsCount() {
        return eventRepository.count();
    }

    // Neues Event erstellen
    public EventDTO createEvent(EventDTO dto) {
        // 1. Validierung
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (dto.getCategory() == null || dto.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        if (dto.getStatus() == null || dto.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // 2. DTO zu Entity konvertieren
        Event entity = EventMapper.toEntity(dto);

        // 3. Repository.save() aufrufen (erkennt automatisch CREATE)
        Event newEvent = eventRepository.save(entity);

        // 4. Gespeicherte Entity zu DTO konvertieren und zurückgeben
        return EventMapper.toDTO(newEvent);
    }

    // Event aktualisieren
    public EventDTO updateEvent(Long id, EventDTO dto) {
        // 1. Prüfen ob Event existiert
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event with id " + id + " not found");
        }

        // 2. DTO zu Entity konvertieren und Id setzen
        Event entity = EventMapper.toEntity(dto);
        entity.setId(id); // <- Wichtig: id setzen für UPDATE-Erkennung

        // 3. Repository.save() aufrufen (erkennt automatisch UPDATE)
        Event updatedEntity = eventRepository.save(entity);

        // 4. Aktualisierte Entity zu DTO konvertieren und zurückgeben
        return EventMapper.toDTO(updatedEntity);
    }

    // Event löschen
    public boolean deleteEvent(Long id) {
        // 1. Prüfen ob Event existiert
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event with id " + id + " not found");
        }

        // 2. Repository.deleteById() aufrufen und Ergebnis zurückgeben
        return eventRepository.deleteById(id);
    }

    // Validierung: Kategorie
    private void validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        List<String> validCategories = List.of("drought", "dustHaze", "earthquakes", "floods",
                "landslides", "manmade", "seaLakeIce", "severeStorms",
                "snow", "tempExtremes", "volcanoes", "waterColor", "wildfires");
        if (!validCategories.contains(category.toLowerCase())) {
            throw new IllegalArgumentException("Category " + category + " is not valid. Valid categories are: " + validCategories);
        }
    }

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
