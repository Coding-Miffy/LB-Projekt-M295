package com.wiss.backend.service;

import com.wiss.backend.dto.EventDTO;
import com.wiss.backend.entity.Event;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import com.wiss.backend.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void whenGetAllEvents_thenReturnListOfEvents() {
        List<Event> mockEvents = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(mockEvents);

        List<Event> result = eventService.getAllEvents();

        assertEquals(2, result.size());
        verify(eventRepository).findAll();
    }

    @Test
    void whenGetAllEventsAsDTO_thenFieldsAreMappedCorrectly() {
        Event event = new Event();
        event.setTitle("Vulkanausbruch Island");
        event.setDate(LocalDate.of(2023, 5, 12));
        event.setCategory(EventCategory.volcanoes);
        event.setLatitude(64.9631);
        event.setLongitude(-19.0208);
        event.setStatus(EventStatus.open);

        when(eventRepository.findAll()).thenReturn(List.of(event));

        List<EventDTO> result = eventService.getAllEventsAsDTO();

        assertEquals(1, result.size());
        EventDTO dto = result.getFirst();
        assertEquals("Vulkanausbruch Island", dto.getTitle());
        assertEquals(LocalDate.of(2023, 5, 12), dto.getDate());
        assertEquals(EventCategory.volcanoes, dto.getCategory());
        assertEquals(64.9631, dto.getLatitude());
        assertEquals(-19.0208, dto.getLongitude());
        assertEquals(EventStatus.open, dto.getStatus());
    }
}
