package com.petko.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petko.recipeapp.exception.ValidationException;
import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientFileService;
import com.petko.recipeapp.services.IngredientsService;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private final IngredientFileService ingredientFileService;
    private static long ingredientID = 0;
    private Map<Long, Ingredients> ingredientsMap = new HashMap<>();
    private final ValidationService validationService;
    private ObjectMapper objectMapper;

    public IngredientsServiceImpl(IngredientFileService ingredientFileService, ValidationService validationService) {
        this.ingredientFileService = ingredientFileService;
        this.validationService = validationService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredients addIngredient(Ingredients ingredients) {
        if (!validationService.validate(ingredients)) {
            throw new ValidationException(ingredients.toString());
        }
        ingredientsMap.put(ingredientID++, ingredients);
        saveToFile();
        return ingredients;
    }

    @Override
    public Optional<Ingredients> getIngredientByID(Long id) {
        return Optional.ofNullable(ingredientsMap.get(id));
    }

    @Override
    public Ingredients update(Long id, Ingredients ingredients) {
        if (!validationService.validate(ingredients)) {
            throw new ValidationException(ingredients.toString());
        }
        ingredientsMap.replace(id, ingredients);
        saveToFile();
        return ingredients;
    }

    @Override
    public Ingredients delete(Long id) {
        ingredientsMap.remove(id);
        saveToFile();
        return null;
    }

    @Override
    public Map<Long, Ingredients> getAll() {
        return ingredientsMap;
    }

    private void saveToFile() {
        try {
            String json = objectMapper.writeValueAsString(ingredientsMap);
            ingredientFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json = ingredientFileService.readFromFile();
            ingredientsMap = objectMapper.readValue(json, new TypeReference<HashMap<Long, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
