package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.exception.ValidationException;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static long recipeID = 0;
    private static final Map<Long, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        return recipeMap.put(recipeID++, recipe);
    }

    @Override
    public Optional<Recipe> getRecipeByID(Long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }
}




