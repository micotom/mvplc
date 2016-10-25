package com.bragi.mvplcapp.mvp.carbrands;

import com.bragi.mvplcapp.data.entities.CarBrand;

public class CarBrandListDisplayModel {
    public final long id;
    public final String name;

    public CarBrandListDisplayModel(CarBrand carBrand) {
        id = carBrand.id;
        name = carBrand.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
