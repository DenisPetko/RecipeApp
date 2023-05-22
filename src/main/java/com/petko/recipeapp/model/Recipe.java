package com.petko.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String recipeID;
    private String title;
    private int cookingTime;
    private List<Ingredients> ingredients;
    private String[] stepsCooking;

}
