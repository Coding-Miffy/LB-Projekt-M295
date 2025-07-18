package com.wiss.backend.repository;

import com.wiss.backend.entity.Event;
import com.wiss.backend.model.EventCategory;
import com.wiss.backend.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Custom Query Methods
    List<Event> findByCategory(EventCategory category);
    List<Event> findByStatus(EventStatus status);
    List<Event> findByDate(LocalDate date);

    // Combined Custom Query Methods
    List<Event> findByCategoryAndStatus(EventCategory category, EventStatus status);
    List<Event> findByDateBetween(LocalDate start, LocalDate end);
    List<Event> findByCategoryAndDateBetween(EventCategory category, LocalDate start, LocalDate end);
    List<Event> findByStatusAndDateBetween(EventStatus status, LocalDate start, LocalDate end);
    List<Event> findByCategoryAndStatusAndDateBetween(EventCategory category, EventStatus status, LocalDate start, LocalDate end);

    // Counting
    long countByCategory(EventCategory category);
    long countByStatus(EventStatus status);
    long countByDateBetween(LocalDate start, LocalDate end);

}
