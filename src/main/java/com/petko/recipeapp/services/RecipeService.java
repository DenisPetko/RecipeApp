package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Recipe;

public interface RecipeService {
    void addRecipe();

    Recipe getRecipe(int recipeID);

}
