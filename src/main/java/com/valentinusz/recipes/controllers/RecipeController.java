package com.valentinusz.recipes.controllers;

import com.valentinusz.recipes.models.Recipe;
import com.valentinusz.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(@Autowired RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public Iterable<Recipe> index() {
        return recipeRepository.findAll();
    }
}
