package com.bragi.mvplcapp.data;


import com.bragi.mvplcapp.data.entities.CarBrand;

import java.util.List;

import rx.Observable;

/**
 * This interface defines a basic data store functionality. It is designed to be asynchronous, i.e.
 * all its methods return immediately and any results are delivered through the returned {@link Observable}
 * It abstracts any underlying implementation which might be a local database or even a remote REST
 * API accessed over network.
 */
public interface DataStore {
    Observable<List<CarBrand>> requestCarBrands();
    Observable<List<CarBrand>> requestCarBrand(long id);
}
