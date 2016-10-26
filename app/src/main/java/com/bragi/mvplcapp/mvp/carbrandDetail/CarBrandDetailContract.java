package com.bragi.mvplcapp.mvp.carbrandDetail;

import com.bragi.mvplc.components.VpContract;

interface CarBrandDetailContract {
    interface View extends VpContract.View {
        void showProgress();
        void hideProgress();
        void setCarBrand(CarBrandDetailDisplayModel carBrand);
    }

    interface Presenter extends VpContract.Presenter<View> {
    }
}
