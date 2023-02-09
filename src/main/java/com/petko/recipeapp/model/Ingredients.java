package com.petko.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredients {
    private String name;
    private int numberOfIngredients;
    private String unitOfMeasurement;
}
