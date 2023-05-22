package com.petko.recipeapp.services.impl;

import com.petko.recipeapp.exception.ValidationException;
import com.petko.recipeapp.model.Ingredients;
import com.petko.recipeapp.services.IngredientsService;
import com.petko.recipeapp.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private static long ingredientID = 0;
    private final Map<Long, Ingredients> ingredientsMap = new HashMap<>();
    private final ValidationService validationService;

    public IngredientsServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Ingredients addIngredient(Ingredients ingredients) {
        if (!validationService.validate(ingredients)) {
            throw new ValidationException(ingredients.toString());
        }
        return ingredientsMap.put(ingredientID++, ingredients);
    }

    @Override
    public Optional<Ingredients> getIngredientByID(Long id) {
        return Optional.ofNullable(ingredientsMap.get(id));
    }

}
