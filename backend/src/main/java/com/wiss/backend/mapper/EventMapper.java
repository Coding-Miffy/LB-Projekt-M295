package com.wiss.backend.mapper;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.entity.Event;

import java.util.List;

public class EventMapper {

    public static EventDTO toDTO(Event entity) {
        if (entity == null) {
            return null;
        }

        // DTO erstellen
        return new EventDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getCategory(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getStatus()
        );
    }

    public static Event toEntity(EventDTO dto) {
        if (dto == null) {
            return null;
        }

        // Entity erstellen
        // (Id wird für PUT getrennt behandelt, für POST ignoriert)
        return new Event(
                dto.getTitle(),
                dto.getCategory(),
                dto.getLongitude(),
                dto.getLatitude(),
                dto.getStatus()
        );
    }

    // Utility Methode für Listen
    public static List<EventDTO> toDTOList(List<Event> entityList) {
        return entityList.stream()
                .map(EventMapper::toDTO)
                .toList();
    }
}
