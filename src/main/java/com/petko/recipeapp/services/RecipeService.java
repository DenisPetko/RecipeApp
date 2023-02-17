package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис по работе с рецептами
 */
public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Optional<Recipe> getRecipeByID(Long id);

    Recipe update(Long id, Recipe recipe);

    Recipe delete(Long id);

    Map<Long, Recipe> getAll();

    Path createRecipeFile() throws IOException;
}
