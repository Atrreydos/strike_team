package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.votes where u.id = :id")
    User findByIdWithVotes(@Param("id") int id);

    User findByLogin(String login);

    int countByEnabled(boolean enabled);
}
