package com.petko.recipeapp.controllers;

import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
            )
    })
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @GetMapping("/downloadRecipes")
    @Operation(
            summary = "Скачать файл с рецептами"
    )
    public ResponseEntity<InputStreamResource> createRecipeFile() {
        try {
            Path path = recipeService.createRecipeFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            } else {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                        .body(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
