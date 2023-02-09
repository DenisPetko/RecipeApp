package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap;
    private static int recipeID = 0;


    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(recipeID++, recipe);
    }

    @Override
    public Recipe getRecipe(int recipeID) {
        return recipeMap.get(recipeID);
    }
}
