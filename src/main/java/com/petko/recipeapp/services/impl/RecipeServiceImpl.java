package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap;


    @Override
    public void addRecipe() {
        recipeMap.put(recipeID, new Recipe());
        recipeID++;
    }

    @Override
    public Recipe getRecipe(int recipeID) {
        return null;
    }
}
