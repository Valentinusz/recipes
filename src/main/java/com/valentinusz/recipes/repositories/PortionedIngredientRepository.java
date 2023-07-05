package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.PortionedIngredient;
import org.springframework.data.repository.CrudRepository;

public interface PortionedIngredientRepository extends CrudRepository<PortionedIngredient, Long> { }
