package com.bragi.mvplcapp.mvp.carbrandDetail;

import com.bragi.mvplc.components.BaseContract;

interface CarBrandDetailContract {
    interface View extends BaseContract.View {
        void showProgress();
        void hideProgress();
        void setCarBrand(CarBrandDetailDisplayModel carBrand);
    }

    interface Presenter extends BaseContract.Presenter {
    }
}
