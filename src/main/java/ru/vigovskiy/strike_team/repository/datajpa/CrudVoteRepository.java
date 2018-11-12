package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.UserEventDayPK;
import ru.vigovskiy.strike_team.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, UserEventDayPK> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") UserEventDayPK id);
}
