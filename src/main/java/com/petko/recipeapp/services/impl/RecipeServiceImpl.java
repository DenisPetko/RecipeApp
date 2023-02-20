package com.petko.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petko.recipeapp.exception.ValidationException;
import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeFileService;
import com.petko.recipeapp.services.RecipeService;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeFileService recipeFileService;
    private static long recipeID = 0;
    private Map<Long, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;
    private final ObjectMapper objectMapper;

    public RecipeServiceImpl(RecipeFileService recipeFileService, ValidationService validationService, ObjectMapper objectMapper) {
        this.recipeFileService = recipeFileService;
        this.validationService = validationService;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.put(recipeID++, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Optional<Recipe> getRecipeByID(Long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.replace(id, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe delete(Long id) {
        recipeMap.remove(id);
        saveToFile();
        return null;
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipeMap;
    }

    @Override
    public Path createRecipeFile() throws IOException {
        Path path = recipeFileService.createTempRecipeFile("recipeFile");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Recipe recipe : recipeMap.values()) {

                writer.append(recipe.getTitle()).append("\n")
                        .append("Время приготовления: ").append(String.valueOf(recipe.getCookingTime())).append("\n")
                        .append("Ингредиенты: ").append("\n");

                for (Ingredients ingredients : recipe.getIngredients()) {
                    writer.append("•").append(ingredients.toString()).append("\n");
                }
                writer.append("Инструкция приготовления: ").append("\n");

                for (String steps : recipe.getStepsCooking()) {
                    writer.append("➣").append(steps).append("\n");
                }
            }
        }
        return path;
    }

    private void saveToFile() {
        try {
            String json = objectMapper.writeValueAsString(recipeMap);
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json = recipeFileService.readFromFile();
            recipeMap = objectMapper.readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}




