package com.wole;

public class ValidationException extends RuntimeException {
    private final String field;
    private final String description;

    public ValidationException(String field, String description) {
        super(description);

        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }
}
