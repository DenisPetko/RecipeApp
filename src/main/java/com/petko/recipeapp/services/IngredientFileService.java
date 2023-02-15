package com.petko.recipeapp.services;

public interface IngredientFileService {

    boolean saveToFile(String json);

    String readFromFile();
}
