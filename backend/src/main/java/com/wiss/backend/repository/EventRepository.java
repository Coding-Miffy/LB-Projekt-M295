package com.wiss.backend.repository;

import com.wiss.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Custom Query Methods
    List<Event> findByCategory(String category);
    List<Event> findByStatus(String status);

}
