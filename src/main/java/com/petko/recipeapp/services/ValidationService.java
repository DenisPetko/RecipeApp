package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.model.Recipe;

/**
 * Сервис валидации
 */

public interface ValidationService {
    boolean validate(Recipe recipe);

    boolean validate(Ingredients ingredients);
}
