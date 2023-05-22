package com.petko.recipeapp.services;

import java.io.File;

public interface IngredientFileService {

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}

