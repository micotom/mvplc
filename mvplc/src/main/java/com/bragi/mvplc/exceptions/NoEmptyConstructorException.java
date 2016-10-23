package com.bragi.mvplc.exceptions;

import android.support.annotation.NonNull;

public class NoEmptyConstructorException extends RuntimeException {

    private NoEmptyConstructorException(final @NonNull String annotationName) {
        super(String.format(
                "Classes annotated with @%s must have an empty constructor", annotationName));
    }

    public NoEmptyConstructorException(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

}
