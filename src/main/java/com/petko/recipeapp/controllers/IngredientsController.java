package com.petko.recipeapp.controllers;
import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "API по работе с ингредиентами", description = "Операции для работы с ингредиентами")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление ингредиентов"
    )
    public ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.addIngredient(ingredients));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по id"
    )
    public ResponseEntity<Ingredients> getIngredientByID(@PathVariable Long id) {
        return ResponseEntity.of(ingredientsService.getIngredientByID(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингредиента по id"
    )
    public ResponseEntity<Ingredients> update(@PathVariable Long id, @RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.update(id, ingredients));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента"
    )
    public ResponseEntity<Ingredients> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientsService.delete(id));
    }

    @GetMapping
    @Operation(
            summary = "Получение всех ингредиентов"
    )
    public ResponseEntity<Map<Long, Ingredients>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }
}
