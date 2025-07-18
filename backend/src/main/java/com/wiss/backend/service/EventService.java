package com.wiss.backend.service;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.dto.EventFormDTO;
import com.wiss.backend.entity.Event;
import com.wiss.backend.exception.CoordinateOutOfRangeException;
import com.wiss.backend.exception.EventNotFoundException;
import com.wiss.backend.exception.FutureDateException;
import com.wiss.backend.exception.InvalidEventDataException;
import com.wiss.backend.mapper.EventMapper;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import com.wiss.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

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
        validateId(id);

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
        validateId(id);

        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
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
        validateEventData(
                dto.getTitle(),
                dto.getDate(),
                dto.getCategory(),
                dto.getLongitude(),
                dto.getLatitude(),
                dto.getStatus()
        );

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

        // Validieren
        validateEventData(
                dto.getTitle(),
                dto.getDate(),
                dto.getCategory(),
                dto.getLongitude(),
                dto.getLatitude(),
                dto.getStatus()
        );

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

    // Für Frontend-Endpoints
    // Alle Events abrufen
    public List<EventFormDTO> getAllEventsAsFormDTO() {
        List<Event> entities = eventRepository.findAll();
        return EventMapper.toFormDTOList(entities);
    }

    // Einzelnes Event abrufen
    public EventFormDTO getEventByIdAsFormDTO(Long id) {
        validateId(id);

        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }

        Event entity = getEventById(id);
        return EventMapper.toFormDTO(entity);
    }

    // Neues Event erstellen
    public EventFormDTO createEventFromForm(Event event) {
        validateEventData(
                event.getTitle(),
                event.getDate(),
                event.getCategory(),
                event.getLongitude(),
                event.getLatitude(),
                event.getStatus()
        );

        Event savedEntity = eventRepository.save(event);
        return EventMapper.toFormDTO(savedEntity);
    }

    // Bestehendes Event editieren
    public EventFormDTO updateEventFromForm(Long id, Event event) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        event.setId(id);

        validateEventData(
                event.getTitle(),
                event.getDate(),
                event.getCategory(),
                event.getLongitude(),
                event.getLatitude(),
                event.getStatus()
        );

        Event updatedEntity = eventRepository.save(event);
        return EventMapper.toFormDTO(updatedEntity);
    }

    // Validierung
    // ID validieren
    private void validateId(Long id) {
        if (id == null) {
            throw new InvalidEventDataException("ID darf nicht null sein.");
        }
    }
    // Titel validieren
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidEventDataException("Titel darf nicht null oder leer sein.");
        }
    }

    // Datum validieren
    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new InvalidEventDataException("Datum darf nicht null sein.");
        }

        if (date.isAfter(LocalDate.now())) {
            throw new FutureDateException(date);
        }
    }

    // Kategorie validieren
    private void validateCategory(EventCategory category) {
        if (category == null) {
            throw new InvalidEventDataException("Kategorie darf nicht null sein.");
        }
    }

    // Longitude validieren
    private void validateLongitude(Double longitude) {
        if (longitude == null) {
            throw new InvalidEventDataException("Longitude darf nicht null sein.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new CoordinateOutOfRangeException("longitude", longitude);
        }
    }

    // Latitude validieren
    private void validateLatitude(Double latitude) {
        if (latitude == null) {
            throw new InvalidEventDataException("Latitude darf nicht null sein.");
        }

        if (latitude < -90 || latitude > 90) {
            throw new CoordinateOutOfRangeException("latitude", latitude);
        }
    }

    // Status validieren
    private void validateStatus(EventStatus status) {
        if (status == null) {
            throw new InvalidEventDataException("Status darf nicht null sein.");
        }
    }

    // Hilfsmethode zur Validierung
    private void validateEventData(String title, LocalDate date, EventCategory category, Double longitude, Double latitude, EventStatus status) {
        validateTitle(title);
        validateDate(date);
        validateCategory(category);
        validateLongitude(longitude);
        validateLatitude(latitude);
        validateStatus(status);
    }

}
