package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int recipeID);

}
