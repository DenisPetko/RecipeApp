package com.petko.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    private String name;
    private int numberOfIngredients;
    private String unitOfMeasurement;
}
