package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")

public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public void addRecipe() {

    }

    @GetMapping
    public Recipe getRecipe(int recipeID) {
        return null;
    }

}
