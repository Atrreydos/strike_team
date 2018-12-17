package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.VoteDay;

public interface CrudVoteDayRepository extends JpaRepository<VoteDay, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM VoteDay vd WHERE vd.id=:id")
    int delete(@Param("id") Integer id);
}
