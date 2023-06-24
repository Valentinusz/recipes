package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> { }
