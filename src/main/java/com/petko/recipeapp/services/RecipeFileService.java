package com.petko.recipeapp.services;

public interface RecipeFileService {

    boolean saveToFile(String json);

    String readFromFile();
}
