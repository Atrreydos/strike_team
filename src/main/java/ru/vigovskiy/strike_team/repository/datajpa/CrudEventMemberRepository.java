package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.EventMember;

public interface CrudEventMemberRepository extends JpaRepository<EventMember, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM EventMember em WHERE em.id=:id")
    int delete(@Param("id") Integer id);

}
