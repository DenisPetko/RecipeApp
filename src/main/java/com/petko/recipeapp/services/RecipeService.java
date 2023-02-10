package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Recipe;

import java.util.Optional;

/**
 * Сервис по работе с рецептами
 */
public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Optional<Recipe> getRecipeByID(Long id);

}
