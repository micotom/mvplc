package com.bragi.mvplcapp.data.entities;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * A model of a Car brand, containing all information related to it. The names of the
 * car brand founders are stored as a list of the specific CarBrandFounder model.
 */
public class CarBrand {

    @NonNull
    public final long id;
    @NonNull
    public final String name;
    @NonNull
    public final String countryCode;
    @NonNull
    public final String logoImageUrl;
    @NonNull
    private final List<CarBrandFounder> founders;

    public CarBrand(long id, @NonNull String name, @NonNull String countryCode,
                    @NonNull String logoImageUrl, @NonNull List<CarBrandFounder> founders) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.logoImageUrl = logoImageUrl;
        this.founders = founders;
    }

    public List<CarBrandFounder> getFounders() {
        return Collections.unmodifiableList(founders);
    }
}
