package com.petko.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FirstController {

    @GetMapping("/")
    public String print() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String printInfo() {
        return """
                Имя ученика: Денис;
                Название проекта: RecipeApp;
                Дата создания: 05.02.2023;
                описание проекта в свободной форме""";
    }

}
