package com.bragi.mvplcapp.mvp.carbrandDetail;

import android.support.annotation.Nullable;

import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.entities.CarBrand;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandListDisplayModel;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class CarBrandDetailPresenter implements CarBrandDetailContract.Presenter {
    private final CarBrandListDisplayModel carBrandListDisplayModel;
    private CarBrandDetailContract.View view;
    private final DataStore dataStore;

    @Nullable
    private Subscription dataStoreSubscription;

    public CarBrandDetailPresenter(CarBrandListDisplayModel carBrandListDisplayModel,
                                   CarBrandDetailContract.View view, DataStore dataStore) {
        this.carBrandListDisplayModel = carBrandListDisplayModel;
        this.view = view;
        this.dataStore = dataStore;
        view.setPresenter(this);
    }

    @Override
    public void onStart() {
        view.setCarBrandName(carBrandListDisplayModel.name);
        reload();
    }

    @Override
    public void onStop() {
        cleanupSubscription();
    }

    private void reload() {
        view.showProgress();

        if (dataStoreSubscription == null) {
            dataStoreSubscription = dataStore.requestCarBrand(carBrandListDisplayModel.id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::foundCarBrandDetail);
        }
    }

    private void foundCarBrandDetail(CarBrand carBrandDetail) {
        view.setCarBrand(new CarBrandDetailDisplayModel(carBrandDetail));
        view.hideProgress();
    }

    private void cleanupSubscription() {
        if (dataStoreSubscription != null && !dataStoreSubscription.isUnsubscribed()) {
            dataStoreSubscription.unsubscribe();
        }
        dataStoreSubscription = null;
    }
}
