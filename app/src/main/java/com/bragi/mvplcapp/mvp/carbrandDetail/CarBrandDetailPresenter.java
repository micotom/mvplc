package com.bragi.mvplcapp.mvp.carbrandDetail;

import android.support.annotation.Nullable;

import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.entities.CarBrand;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

class CarBrandDetailPresenter implements CarBrandDetailContract.Presenter {
    private final long carBrandId;
    private CarBrandDetailContract.View view;
    private final DataStore dataStore;

    @Nullable
    private Subscription dataStoreSubscription;

    CarBrandDetailPresenter(long carBrandId,
                            CarBrandDetailContract.View view, DataStore dataStore) {
        this.carBrandId = carBrandId;
        this.view = view;
        this.dataStore = dataStore;
    }

    @Override
    public void onStart() {
        reload();
    }

    @Override
    public void onStop() {
        cleanupSubscription();
    }

    private void reload() {
        view.showProgress();

        if (dataStoreSubscription == null) {
            dataStoreSubscription = dataStore.requestCarBrand(carBrandId)
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
