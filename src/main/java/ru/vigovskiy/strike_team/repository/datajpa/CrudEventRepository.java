package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.Event;

public interface CrudEventRepository extends JpaRepository<Event, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Event e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Query(value = "SELECT e FROM Event e LEFT JOIN FETCH e.eventDays where e.id = :id")
    Event findByIdWithEventDays(@Param("id") int id);
}
