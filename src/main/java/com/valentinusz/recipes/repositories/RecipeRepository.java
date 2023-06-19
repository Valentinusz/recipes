package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> { }
