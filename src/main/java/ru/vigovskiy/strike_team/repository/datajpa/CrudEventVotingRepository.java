package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.EventVoting;

public interface CrudEventVotingRepository extends JpaRepository<EventVoting, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Event e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Query(value = "SELECT ev FROM EventVoting ev LEFT JOIN FETCH ev.voteDays where ev.id = :id")
    EventVoting findByIdWithEventDays(@Param("id") int id);
}
