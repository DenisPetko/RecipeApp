package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.model.Recipe;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null && recipe.getTitle() != null
                && recipe.getStepsCooking() != null
                && recipe.getIngredients() != null;
    }

    @Override
    public boolean validate(Ingredients ingredients) {
        return ingredients != null;
    }
}
