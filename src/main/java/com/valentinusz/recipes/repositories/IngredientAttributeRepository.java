package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.IngredientAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientAttributeRepository extends JpaRepository<IngredientAttribute, Integer> {
}
