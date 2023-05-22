package com.petko.recipeapp.exception;

/**
 * Ошибка валидации
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String entity) {
        super("Ошибка валидации " + entity);
    }
}
