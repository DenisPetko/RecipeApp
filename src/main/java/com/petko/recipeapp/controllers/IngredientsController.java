package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.addIngredient(ingredients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredients> getIngredientByID(@PathVariable Long id) {
        return ResponseEntity.of(ingredientsService.getIngredientByID(id));
    }
}
