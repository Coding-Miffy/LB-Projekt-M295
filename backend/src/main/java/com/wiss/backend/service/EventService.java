package com.wiss.backend.service;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.dto.EventFormDTO;
import com.wiss.backend.entity.Event;
import com.wiss.backend.exception.CategoryNotFoundException;
import com.wiss.backend.exception.EventNotFoundException;
import com.wiss.backend.exception.StatusNotFoundException;
import com.wiss.backend.mapper.EventMapper;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import com.wiss.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    // Ein spezifisches Event finden (Exception-Handling umgesetzt)
    public EventDTO getEventByIdAsDTO(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Event entity = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        return EventMapper.toDTO(entity);
    }

    // Events nach Kategorien filtern
    public List<EventDTO> getEventsByCategoryAsDTO(EventCategory category) {
        List<Event> entities = eventRepository.findByCategory(category);
        return EventMapper.toDTOList(entities);
    }

    // Events nach Status filtern
    public List<EventDTO> getEventsByStatusAsDTO(EventStatus status) {

        List<Event> entities = eventRepository.findByStatus(status);
        return EventMapper.toDTOList(entities);
    }

    // Events nach Datum filtern
    public List<EventDTO> getEventsByDateAsDTO(LocalDate date) {
        List<Event> entities = eventRepository.findByDate(date);
        return EventMapper.toDTOList(entities);
    }


    // Weitere Methoden
    // Alle Events abrufen
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Ein spezifisches Event finden (Exception-Handling umgesetzt)
    public Event getEventById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            throw new EventNotFoundException(id);
        }

        return optionalEvent.get();
    }

    // Events nach Kategorien filtern
    public List<Event> getEventsByCategory(EventCategory category) {
        return eventRepository.findByCategory(category);
    }

    // Events nach Status filtern
    public List<Event> getEventsByStatus(EventStatus status) {

        return eventRepository.findByStatus(status);
    }

    // Events nach Datum filtern
    public List<Event> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    // Anzahl aller Events
    public long getTotalEventsCount() {
        return eventRepository.count();
    }

    // Neues Event erstellen
    public EventDTO createEvent(EventDTO dto) {
        // 1. Validierung
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (dto.getCategory() == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (dto.getStatus() == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        // 2. DTO zu Entity konvertieren
        Event entity = EventMapper.toEntity(dto);

        // 3. Repository.save() aufrufen (erkennt automatisch CREATE)
        Event newEvent = eventRepository.save(entity);

        // 4. Gespeicherte Entity zu DTO konvertieren und zurückgeben
        return EventMapper.toDTO(newEvent);
    }

    // Event aktualisieren (Exception-Handling umgesetzt)
    public EventDTO updateEvent(Long id, EventDTO dto) {
        // 1. Prüfen ob Event existiert
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }

        // 2. DTO zu Entity konvertieren und Id setzen
        Event entity = EventMapper.toEntity(dto);
        entity.setId(id); // <- Wichtig: id setzen für UPDATE-Erkennung

        // 3. Repository.save() aufrufen (erkennt automatisch UPDATE)
        Event updatedEntity = eventRepository.save(entity);

        // 4. Aktualisierte Entity zu DTO konvertieren und zurückgeben
        return EventMapper.toDTO(updatedEntity);
    }

    // Event löschen (Exception-Handling umgesetzt)
    public void deleteEvent(Long id) {
        // 1. Prüfen ob Event existiert
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }

        // 2. Repository.deleteById() aufrufen und Ergebnis zurückgeben
        eventRepository.deleteById(id);
    }

    // Custom Queries
    // Kategorie + Status
    public List<EventDTO> getEventsByCategoryAndStatus(EventCategory category, EventStatus status) {
        List<Event> entities = eventRepository.findByCategoryAndStatus(category, status);
        return EventMapper.toDTOList(entities);
    }

    // Startdatum + Enddatum
    public List<EventDTO> getEventsByDateBetween(LocalDate start, LocalDate end) {
        List<Event> entities = eventRepository.findByDateBetween(start, end);
        return EventMapper.toDTOList(entities);
    }

    // Kategorie + Startdatum + Enddatum
    public List<EventDTO> getEventsByCategoryAndDateBetween(EventCategory category, LocalDate start, LocalDate end) {
        List<Event> entities = eventRepository.findByCategoryAndDateBetween(category, start, end);
        return EventMapper.toDTOList(entities);
    }

    // Status + Startdatum + Enddatum
    public List<EventDTO> getEventsByStatusAndDateBetween(EventStatus status, LocalDate start, LocalDate end) {


        List<Event> entities = eventRepository.findByStatusAndDateBetween(status, start, end);
        return EventMapper.toDTOList(entities);
    }

    // Kategorie + Status + Startdatum + Enddatum
    public List<EventDTO> getEventsByCategoryAndStatusAndDateBetween(EventCategory category, EventStatus status, LocalDate start, LocalDate end) {
        List<Event> entities = eventRepository.findByCategoryAndStatusAndDateBetween(category, status, start, end);
        return EventMapper.toDTOList(entities);
    }

    // Anzahl nach Kategorie
    public long getTotalEventsByCategory(EventCategory category) {
        return eventRepository.countByCategory(category);
    }

    // Anzahl nach Status
    public long getTotalEventsByStatus(EventStatus status) {

        return eventRepository.countByStatus(status);
    }

    // Anzahl nach Zeitraum
    public long getTotalEventsByDateBetween(LocalDate start, LocalDate end) {
        return eventRepository.countByDateBetween(start, end);
    }

    // Nach Kategorie, geordnet nach Datum
    public List<EventDTO> getEventsByCategoryOrderByDateDesc(EventCategory category) {
        List<Event> entities = eventRepository.findByCategoryOrderByDateDesc(category);
        return EventMapper.toDTOList(entities);
    }

    // Nach Status, geordnet nach Datum
    public List<EventDTO> getEventsByStatusOrderByDateDesc(EventStatus status) {

        List<Event> entities = eventRepository.findByStatusOrderByDateDesc(status);
        return EventMapper.toDTOList(entities);
    }

    // Nach Datum, geordnet nach Datum
    public List<EventDTO> getEventsByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end) {
        List<Event> entities = eventRepository.findByDateBetweenOrderByDateDesc(start, end);
        return EventMapper.toDTOList(entities);
    }

    // Für Frontend-Endpoints
    // Alle Events abrufen
    public List<EventFormDTO> getAllEventsAsFormDTO() {
        List<Event> entities = eventRepository.findAll();
        return EventMapper.toFormDTOList(entities);
    }

    // Einzelnes Event abrufen
    public EventFormDTO getEventByIdAsFormDTO(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }

        Event entity = getEventById(id);
        return EventMapper.toFormDTO(entity);
    }

    // Neues Event erstellen
    public EventFormDTO createEventFromForm(Event event) {
        Event savedEntity = eventRepository.save(event);
        return EventMapper.toFormDTO(savedEntity);
    }

    // Bestehendes Event editieren
    public EventFormDTO updateEventFromForm(Long id, Event event) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        event.setId(id);

        Event updatedEntity = eventRepository.save(event);
        return EventMapper.toFormDTO(updatedEntity);
    }

}
