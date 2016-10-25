package com.bragi.mvplcapp.mvp.carbrands;

import android.support.annotation.Nullable;

import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.entities.CarBrand;
import com.bragi.mvplcapp.utils.Navigator;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

class CarBrandsPresenter implements CarBrandsContract.Presenter {
    private final CarBrandsContract.View view;
    private final DataStore dataStore;
    private final Navigator navigator;

    @Nullable
    private Subscription dataStoreSubscription;

    public CarBrandsPresenter(CarBrandsContract.View view, DataStore dataStore, Navigator navigator) {
        this.view = view;
        this.dataStore = dataStore;
        this.navigator = navigator;
        view.setPresenter(this);
    }

    // CarBrandsPresenterInput

    @Override
    public void start() {
        reload();
    }

    @Override
    public void stop() {
        cleanupSubscription();
    }

    @Override
    public void refreshRequested() {
        reload();
    }

    private void reload() {
        view.showProgress();
        view.clearCarBrands();

        if (dataStoreSubscription == null) {
            dataStoreSubscription = dataStore.requestCarBrands()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::foundCarBrands);
        }
    }

    @Override
    public void carBrandClicked(CarBrandListDisplayModel displayModel) {
        navigator.navigateToCarbrandDetail(displayModel);
    }

    private void foundCarBrands(List<CarBrand> carBrands) {
        cleanupSubscription();

        // Transform to display data
        Observable.from(carBrands)
                .map(CarBrandListDisplayModel::new)
                .toList()
                .subscribe(view::setCarBrands);
        view.hideProgress();
    }

    private void cleanupSubscription() {
        if (dataStoreSubscription != null && !dataStoreSubscription.isUnsubscribed()) {
            dataStoreSubscription.unsubscribe();
        }
        dataStoreSubscription = null;
    }
}
