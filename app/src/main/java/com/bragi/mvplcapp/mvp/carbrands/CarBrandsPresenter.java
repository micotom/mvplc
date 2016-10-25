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
    private final DataStore dataStore;
    private final Navigator navigator;

    private CarBrandsContract.View view;

    @Nullable
    private Subscription dataStoreSubscription;

    CarBrandsPresenter(DataStore dataStore, Navigator navigator) {
        this.dataStore = dataStore;
        this.navigator = navigator;
    }

    @Override
    public void onStart(CarBrandsContract.View view) {
        this.view = view;
        reload();
    }

    @Override
    public void onStop() {
        cleanupSubscription();
    }

    @Override
    public void refreshRequested() {
        reload();
    }

    @Override
    public void carBrandClicked(CarBrandListDisplayModel displayModel) {
        navigator.navigateToCarbrandDetail(displayModel.id);
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
