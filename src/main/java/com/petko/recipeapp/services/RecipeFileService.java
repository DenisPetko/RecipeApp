package com.petko.recipeapp.services;

import java.io.File;

public interface RecipeFileService {

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
