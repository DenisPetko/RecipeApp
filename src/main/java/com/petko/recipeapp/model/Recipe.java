package com.petko.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private List<Ingredients> ingredients;
    private List<String> stepsCooking;

}
