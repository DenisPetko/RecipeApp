package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")

public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipes(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeByID(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getRecipeByID(id));
    }

}
