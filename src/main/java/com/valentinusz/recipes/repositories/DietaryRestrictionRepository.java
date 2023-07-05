package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.DietaryRestriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaryRestrictionRepository extends JpaRepository<DietaryRestriction, Integer> {
}
