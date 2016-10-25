package com.bragi.mvplcapp.mvp.carbrandDetail;

import com.bragi.mvplc.components.BaseContract;

public interface CarBrandDetailContract {
    interface View extends BaseContract.View<Presenter> {

    }

    interface Presenter extends BaseContract.Presenter {
        void showProgress();
        void hideProgress();
        void setCarBrand(CarBrandDetailDisplayModel carBrand);
        void setCarBrandName(String carBrandName);
    }
}
