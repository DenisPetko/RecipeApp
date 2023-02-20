package com.petko.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface RecipeFileService {

    Path createTempRecipeFile(String suffix);

    boolean saveToFile(String json);

    String readFromFile(MultipartFile file);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
