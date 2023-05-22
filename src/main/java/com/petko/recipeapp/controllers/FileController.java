package com.petko.recipeapp.controllers;

import com.petko.recipeapp.services.IngredientFileService;
import com.petko.recipeapp.services.RecipeFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "API для работы с файлами", description = "Импорт и экспорт файлов")
public class FileController {

    private final RecipeFileService recipeFileService;
    private final IngredientFileService ingredientFileService;


    public FileController(RecipeFileService recipeFileService, IngredientFileService ingredientFileService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFileService = ingredientFileService;
    }

    @GetMapping("/recipe/export")
    @Operation(
            summary = "Выгрузка файла рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл сохранен"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Файл пустой"
            )
    })
    public ResponseEntity<InputStreamResource> downloadFileRecipes() throws FileNotFoundException {
        File recipeDataFile = recipeFileService.getDataFile();
        if (recipeDataFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeDataFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(recipeDataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/ingredient/export")
    @Operation(
            summary = "Выгрузка файла ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл сохранен"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Файл пустой"
            )
    })
    public ResponseEntity<InputStreamResource> downloadFileIngredients() throws FileNotFoundException {
        File ingredientDataFile = ingredientFileService.getDataFile();
        if (ingredientDataFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(ingredientDataFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(ingredientDataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/recipe/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка файла рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл загружен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос"
            )
    })
    public ResponseEntity<Void> uploadFileRecipe(@RequestParam MultipartFile file) {
        recipeFileService.cleanDataFile();
        File recipeDataFile = recipeFileService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(recipeDataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/ingredient/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка файла ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл загружен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос"
            )
    })
    public ResponseEntity<Void> uploadFileIngredient(@RequestParam MultipartFile file) {
        ingredientFileService.cleanDataFile();
        File ingredientDataFile = ingredientFileService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientDataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
