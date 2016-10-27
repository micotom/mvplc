package com.bragi.mvplcapp.mvp.carbrands;

import com.bragi.mvplc.components.VpPresenter;
import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.entities.CarBrand;
import com.bragi.mvplcapp.utils.Navigator;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

class CarBrandsPresenter extends VpPresenter<CarBrandsContract.View> implements CarBrandsContract.Presenter {
    private final DataStore dataStore;
    private final Navigator navigator;

    private CarBrandsContract.View view;

    private boolean isLoading = false;

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
        // do nothing
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

        if (isLoading) {
            return;
        }
        isLoading = true;

        addSubscription(dataStore.requestCarBrands()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::foundCarBrands)
        );
    }

    private void foundCarBrands(List<CarBrand> carBrands) {
        isLoading = false;

        // Transform to display data
        Observable.from(carBrands)
                .map(CarBrandListDisplayModel::new)
                .toList()
                .subscribe(view::setCarBrands);
        view.hideProgress();
    }
}
