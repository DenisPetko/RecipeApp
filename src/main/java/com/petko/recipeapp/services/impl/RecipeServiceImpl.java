package com.petko.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petko.recipeapp.exception.ValidationException;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeFileService;
import com.petko.recipeapp.services.RecipeService;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeFileService recipeFileService;
    private static long recipeID = 0;
    private Map<Long, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;

    public RecipeServiceImpl(RecipeFileService recipeFileService, ValidationService validationService) {
        this.recipeFileService = recipeFileService;
        this.validationService = validationService;
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
        saveToFile();
        return recipeMap.replace(id, recipe);
    }

    @Override
    public Recipe delete(Long id) {
        return recipeMap.remove(id);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipeMap;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = recipeFileService.readFromFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}




