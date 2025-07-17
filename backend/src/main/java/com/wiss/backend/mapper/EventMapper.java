package com.wiss.backend.mapper;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.dto.EventFormDTO;
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
                entity.getDate(),
                entity.getCategory(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getStatus()
        );
    }

    public static EventFormDTO toFormDTO(Event entity) {
        if (entity == null) {
            return null;
        }

        return new EventFormDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
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
                dto.getDate(),
                dto.getCategory(),
                dto.getLongitude(),
                dto.getLatitude(),
                dto.getStatus()
        );
    }

    // Utility Methoden für Listen
    public static List<EventDTO> toDTOList(List<Event> entityList) {
        return entityList.stream()
                .map(EventMapper::toDTO)
                .toList();
    }

    public static List<EventFormDTO> toFormDTOList(List<Event> entityList) {
        return entityList.stream()
                .map(EventMapper::toFormDTO)
                .toList();
    }
}
