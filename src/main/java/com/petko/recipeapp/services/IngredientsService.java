package com.petko.recipeapp.services;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.model.Recipe;

public interface IngredientsService {

    void addIngredient(Ingredients ingredients);

    Ingredients getIngredient(int recipeID);
}
