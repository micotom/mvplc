package com.bragi.mvplcapp.mvp.carbrandDetail;

import com.bragi.mvplc.components.VpPresenter;
import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.entities.CarBrand;

import rx.android.schedulers.AndroidSchedulers;

class CarBrandDetailPresenter extends VpPresenter<CarBrandDetailContract.View> implements CarBrandDetailContract.Presenter {
    private final long carBrandId;
    private final DataStore dataStore;

    private CarBrandDetailContract.View view;

    private boolean isLoading = false;

    CarBrandDetailPresenter(long carBrandId, DataStore dataStore) {
        this.carBrandId = carBrandId;
        this.dataStore = dataStore;
    }

    @Override
    public void onStart(CarBrandDetailContract.View view) {
        this.view = view;
        reload();
    }

    @Override
    public void onStop() {
        // do nothing
    }

    private void reload() {
        view.showProgress();

        if (isLoading) {
            return;
        }
        isLoading = true;

        addSubscription(dataStore.requestCarBrand(carBrandId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::foundCarBrandDetail)
        );
    }

    private void foundCarBrandDetail(CarBrand carBrandDetail) {
        isLoading = false;

        view.setCarBrand(new CarBrandDetailDisplayModel(carBrandDetail));
        view.hideProgress();
    }
}
