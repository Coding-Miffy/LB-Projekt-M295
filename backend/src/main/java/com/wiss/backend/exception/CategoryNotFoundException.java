package com.wiss.backend.exception;

/**
 * Exception für ungültige Event-Kategorien.
 */
public class CategoryNotFoundException extends RuntimeException{

    private final String category;

    /**
     * Erstellt eine neue CategoryNotFoundException.
     *
     * @param category Kategorie, die nicht gefunden werden konnte.
     */
    public CategoryNotFoundException(String category) {
        super("Category " + category + " not found");
        this.category = category;
    }

    /**
     * @return Kategorie, die nicht gefunden werden konnte.
     */
    public String getCategory() {
        return category;
    }
}
