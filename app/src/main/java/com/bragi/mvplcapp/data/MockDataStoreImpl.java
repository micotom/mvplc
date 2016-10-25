package com.bragi.mvplcapp.data;

import android.support.annotation.WorkerThread;

import com.bragi.mvplcapp.data.entities.CarBrand;
import com.bragi.mvplcapp.data.entities.CarBrandFounder;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * A mock implementation of a data store filled with mock data stored in memory.
 * Any delays while retrieving the data are simulated through sleeping.
 */
public class MockDataStoreImpl implements DataStore {

    private static final long FILTER_ID_NONE = -1;

    private long filterCarBrandId = FILTER_ID_NONE;

    // DataStore
    @Override
    public Observable<List<CarBrand>> requestCarBrands() {
        filterCarBrandId = FILTER_ID_NONE;
        return Observable.defer(() -> Observable.just(slowLoadCarbrands()))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<CarBrand>> requestCarBrand(long id) {
        if (id < 0) {
            throw new InvalidParameterException("Invalid negative CarBrand id: " + id);
        }
        filterCarBrandId = id;
        return Observable.defer(() -> Observable.just(slowLoadCarbrands()))
                .subscribeOn(Schedulers.io());
    }

    // Simulated slow data loading

    @WorkerThread
    private List<CarBrand> slowLoadCarbrands() {
        List<CarBrand> resultSet = lazyInitStoredCarBrands();

        if(filterCarBrandId != FILTER_ID_NONE) {
            resultSet = filterId(filterCarBrandId, resultSet);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        return resultSet;
    }

    // Helper methods for filtering the data

    private static List<CarBrand> filterId(long id, List<CarBrand> carBrands) {
        List<CarBrand> result = new ArrayList<>();
        for(CarBrand brand : carBrands) {
            if(brand.id == id) {
                result.add(brand);
                break;
            }
        }
        return result;
    }

    // Mock data initialization

    private List<CarBrand> lazyInitStoredCarBrands() {
        if (storedCarBrands.isEmpty()) {
            initializeDummyData();
        }
        return storedCarBrands;
    }

    private final List<CarBrand> storedCarBrands = new ArrayList<>();

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private void initializeDummyData() {
        long id = 0;

        List<CarBrandFounder> founders = Arrays.asList(
                new CarBrandFounder("Alexandre", "Darracq"),
                new CarBrandFounder("Ugo", "Stella"),
                new CarBrandFounder("Nicola", "Romeo")
        );
        storedCarBrands.add(
                new CarBrand(id++, "Alfa Romeo", "IT",
                        "http://upload.wikimedia.org/wikipedia/en/thumb/2/24/Alfa_Romeo.svg/500px-Alfa_Romeo.svg.png",
                        founders));

        founders = Arrays.asList(
                new CarBrandFounder("Lionel", "Martin"),
                new CarBrandFounder("Robert", "Bamford")
        );

        storedCarBrands.add(new CarBrand(id++, "Aston Martin", "GB",
                "http://upload.wikimedia.org/wikipedia/en/thumb/e/e9/AstonMartinLogo.svg/500px-AstonMartinLogo.svg.png",
                founders));


        founders = Arrays.asList(
                new CarBrandFounder("August", "Horch")
        );
        storedCarBrands.add(new CarBrand(id++, "Audi", "DE",
                "http://upload.wikimedia.org/wikipedia/en/0/04/Audi_AG_logo.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("H. M.", "Bentley"),
                new CarBrandFounder("W. O.", "Bentley")
        );
        storedCarBrands.add(new CarBrand(id++, "Bentley", "GB",
                "http://upload.wikimedia.org/wikipedia/en/thumb/6/6c/Bentley_logo.svg/500px-Bentley_logo.svg.png",
                founders));


        founders = Arrays.asList(
                new CarBrandFounder("Franz Josef", "Popp"),
                new CarBrandFounder("Karl", "Rapp"),
                new CarBrandFounder("Camillo", "Castiglioni")
        );
        storedCarBrands.add(new CarBrand(id++, "BMW", "DE",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/500px-BMW.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Wolfgang", "Dürheimer")
        );
        storedCarBrands.add(new CarBrand(id++, "Bugatti", "FR",
                "http://upload.wikimedia.org/wikipedia/en/thumb/6/60/Bugatti_logo.svg/500px-Bugatti_logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Enzo", "Ferrari")
        );
        storedCarBrands.add(new CarBrand(id++, "Ferrari", "IT",
                "http://upload.wikimedia.org/wikipedia/en/thumb/d/d1/Ferrari-Logo.svg/500px-Ferrari-Logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Giovanni", "Agnelli")
        );
        storedCarBrands.add(new CarBrand(id++, "Fiat", "IT",
                "http://upload.wikimedia.org/wikipedia/en/thumb/9/96/Fiat_Logo.svg/500px-Fiat_Logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Henry", "Ford")
        );
        storedCarBrands.add(new CarBrand(id++, "Ford", "US",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Ford_Motor_Company_Logo.svg/500px-Ford_Motor_Company_Logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Ferruccio", "Lamborghini")
        );
        storedCarBrands.add(new CarBrand(id++, "Lamborghini", "IT",
                "http://upload.wikimedia.org/wikipedia/en/thumb/d/df/Lamborghini_Logo.svg/500px-Lamborghini_Logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Eiji", "Toyoda")
        );
        storedCarBrands.add(new CarBrand(id++, "Lexus", "JP",
                "http://upload.wikimedia.org/wikipedia/en/thumb/d/d1/Lexus_division_emblem.svg/500px-Lexus_division_emblem.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Karl", "Benz"),
                new CarBrandFounder("Gottlieb", "Daimler")
        );
        storedCarBrands.add(new CarBrand(id++, "Mercedes-Benz", "DE" +
                "", "http://upload.wikimedia.org/wikipedia/en/b/bb/Mercedes_benz_silverlogo.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Iwasaki", "Yatarō")
        );
        storedCarBrands.add(new CarBrand(id++, "Mitsubishi", "JP",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Mitsubishi_logo.svg/500px-Mitsubishi_logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Adam", "Opel")
        );
        storedCarBrands.add(new CarBrand(id++, "Opel", "DE",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Opel-Logo-2011-Slogan-Vector.svg/500px-Opel-Logo-2011-Slogan-Vector.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Ferdinand", "Porsche")
        );
        storedCarBrands.add(new CarBrand(id++, "Porsche", "DE",
                "http://upload.wikimedia.org/wikipedia/en/7/73/Porsche_logotype.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Kenji", "Kita")
        );
        storedCarBrands.add(new CarBrand(id++, "Subaru", "JP",
                "http://upload.wikimedia.org/wikipedia/en/thumb/4/47/Subaru_logo.svg/500px-Subaru_logo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Elon", "Musk")
        );
        storedCarBrands.add(new CarBrand(id++, "Tesla", "US",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Tesla_Motors.svg/465px-Tesla_Motors.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Kiichiro", "Toyoda")
        );
        storedCarBrands.add(new CarBrand(id++, "Toyota", "JP",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Toyota_carlogo.svg/500px-Toyota_carlogo.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("German Labour Front", "")
        );
        storedCarBrands.add(new CarBrand(id++, "Volkswagen", "DE",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Volkswagen_logo_2012.svg/500px-Volkswagen_logo_2012.svg.png",
                founders));

        founders = Arrays.asList(
                new CarBrandFounder("Gustav", "Larson"),
                new CarBrandFounder("Assar", "Gabrielsson")
        );
        storedCarBrands.add(new CarBrand(id++, "Volvo", "SE",
                "http://upload.wikimedia.org/wikipedia/en/9/9c/Volvo_Cars_logo.png",
                founders));
    }
}
