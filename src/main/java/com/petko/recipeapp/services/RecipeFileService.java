package com.petko.recipeapp.services;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFileService {

    Path createTempRecipeFile(String suffix);

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
