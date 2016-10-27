package com.bragi.mvplcapp.mvp.carbrandDetail;

import com.bragi.mvplcapp.data.entities.CarBrand;
import com.bragi.mvplcapp.data.entities.CarBrandFounder;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandListDisplayModel;
import com.bragi.mvplcapp.utils.CountryNamesUtils;

class CarBrandDetailDisplayModel extends CarBrandListDisplayModel {
    final String countryName;
    final String logoImageUrl;
    final String foundersNames;

    CarBrandDetailDisplayModel(CarBrand carBrand) {
        super(carBrand);
        countryName = CountryNamesUtils.getCountryName(carBrand.countryCode);
        logoImageUrl = carBrand.logoImageUrl;

        StringBuilder sb = new StringBuilder();
        for (CarBrandFounder carBrandFounder : carBrand.getFounders()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(carBrandFounder.firstName).append(" ").append(carBrandFounder.lastName);
        }
        foundersNames = sb.toString();
    }
}
