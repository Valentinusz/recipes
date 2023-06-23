package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> { }
