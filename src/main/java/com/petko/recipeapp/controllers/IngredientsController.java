package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request/обнаружена ошибка в синтаксисе запроса"
            )
    })
    public ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.addIngredient(ingredients));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден"
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
    public ResponseEntity<Ingredients> getIngredientByID(@PathVariable Long id) {
        return ResponseEntity.of(ingredientsService.getIngredientByID(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингредиента по id"
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
    public ResponseEntity<Ingredients> update(@PathVariable Long id, @RequestBody Ingredients ingredients) {
        return ResponseEntity.ok(ingredientsService.update(id, ingredients));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента"
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
    public ResponseEntity<Ingredients> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientsService.delete(id));
    }

    @GetMapping
    @Operation(
            summary = "Получение всех ингредиентов"
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
    public ResponseEntity<Map<Long, Ingredients>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }
}
