package com.wiss.backend.repository;

import com.wiss.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Custom Query Methods
    List<Event> findByCategory(String category);
    List<Event> findByStatus(String status);
    List<Event> findByDate(LocalDate date);

    // Combined Custom Query Methods
    List<Event> findByCategoryAndStatus(String category, String status);
    List<Event> findByDateBetween(LocalDate start, LocalDate end);
    List<Event> findByCategoryAndDateBetween(String category, LocalDate start, LocalDate end);
    List<Event> findByStatusAndDateBetween(String status, LocalDate start, LocalDate end);
    List<Event> findByCategoryAndStatusAndDateBetween(String category, String status, LocalDate start, LocalDate end);

    // Counting
    long countByCategory(String category);
    long countByStatus(String status);
    long countByDateBetween(LocalDate start, LocalDate end);

    // Sorting
    List<Event> findByCategoryOrderByDateDesc(String category);
    List<Event> findByStatusOrderByDateDesc(String status);
    List<Event> findByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);


}
