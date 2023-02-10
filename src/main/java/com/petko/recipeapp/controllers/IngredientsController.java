package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<Ingredients> update(@PathVariable Long id, @RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.update(id, ingredients));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredients> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientsService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Ingredients>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }
}
