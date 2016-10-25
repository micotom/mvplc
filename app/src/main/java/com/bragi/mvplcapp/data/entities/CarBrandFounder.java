package com.bragi.mvplcapp.data.entities;

import android.support.annotation.NonNull;

/**
 * A model containing the names of a single car brand founder
 */
public class CarBrandFounder {

    @NonNull
    public final String firstName;
    @NonNull
    public final String lastName;

    public CarBrandFounder(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
