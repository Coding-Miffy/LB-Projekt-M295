package com.wiss.backend.repository;

import com.wiss.backend.entity.Event;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class EventRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void whenSaveEvent_thenCanFindById() {
        Event event = new Event(
                "Vulkanausbruch Island",
                LocalDate.of(2023, 5, 12),
                EventCategory.volcanoes,
                64.9631,
                -19.0208,
                EventStatus.open
        );

        Event saved = entityManager.persistAndFlush(event);
        Optional<Event> found = eventRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Vulkanausbruch Island");
        assertThat(found.get().getCategory()).isEqualTo(EventCategory.volcanoes);
    }

    @Test
    public void whenFindByCategory_thenReturnMatchingEvents() {
        Event event1 = new Event(
                "Waldbrand Kalifornien",
                LocalDate.of(2022, 8, 20),
                EventCategory.wildfires,
                36.7783,
                -119.4179,
                EventStatus.open
        );

        Event event2 = new Event(
                "Waldbrand Australien",
                LocalDate.of(2021, 12, 15),
                EventCategory.wildfires,
                -25.2744,
                133.7751,
                EventStatus.closed
        );

        Event otherEvent = new Event(
                "Erdbeben Türkei",
                LocalDate.of(2023, 2, 6),
                EventCategory.earthquakes,
                38.4237,
                27.1428,
                EventStatus.open
        );

        entityManager.persist(event1);
        entityManager.persist(event2);
        entityManager.persist(otherEvent);
        entityManager.flush();

        var result = eventRepository.findByCategory(EventCategory.wildfires);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getCategory()).isEqualTo(EventCategory.wildfires);
        assertThat(result.get(1).getCategory()).isEqualTo(EventCategory.wildfires);
    }
}
