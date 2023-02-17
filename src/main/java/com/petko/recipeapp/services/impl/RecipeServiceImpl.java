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
import java.io.File;
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
    public File readFile() {
        return null;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json = recipeFileService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}




