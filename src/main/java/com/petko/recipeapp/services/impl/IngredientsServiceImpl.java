package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private static Map<Integer, Ingredients> ingredientsMap;
    private static int ingredientID = 0;

    @Override
    public void addIngredient(Ingredients ingredients) {
        ingredientsMap.put(ingredientID++, ingredients);
    }

    @Override
    public Ingredients getIngredient(int ingredientID) {
        return ingredientsMap.get(ingredientID);
    }
}
