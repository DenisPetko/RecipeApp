package com.petko.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IngredientFileService {

    boolean saveToFile(String json);

    String readFromFile(MultipartFile file);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
