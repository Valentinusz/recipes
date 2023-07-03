package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/** Repository for the user entity. */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds the given user and loads their dietary restrictions.
     * @param id id of the user to fetch information of.
     * @return Optional instance.
     */
    @Query("SELECT u FROM User u JOIN FETCH u.dietaryRestrictions WHERE u.id = :id")
    Optional<User> findUserByIdWithDietaryRestrictions(@Param("id") Long id);
}
