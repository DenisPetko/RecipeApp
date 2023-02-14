package com.petko.recipeapp.controllers;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "API по работе с рецептами", description = "Операции для работы с рецептами")

public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            )
    })

    public ResponseEntity<Recipe> addRecipes(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found/запрашиваемой страницы нет"
            )
    })
    public ResponseEntity<Recipe> getRecipeByID(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getRecipeByID(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found/запрашиваемой страницы нет"
            )
    })
    public ResponseEntity<Recipe> update(@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.update(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found/запрашиваемой страницы нет"
            )

    })
    public ResponseEntity<Recipe> delete(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping
    @Operation(
            summary = "Получение всех рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все рецепты "
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found/запрашиваемой страницы нет"
            )
    })
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }

}
