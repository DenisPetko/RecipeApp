package com.petko.recipeapp.controllers;

import com.petko.recipeapp.services.IngredientsService;
import com.petko.recipeapp.model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public void addIngredient() {

    }

    @GetMapping
    public Recipe getIngredient() {
        return null;
    }
}
