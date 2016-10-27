package com.bragi.mvplcapp.mvp.carbrands;

import com.bragi.mvplc.components.VpContract;

import java.util.List;

interface CarBrandsContract {
    interface View extends VpContract.View {
        void showProgress();
        void hideProgress();
        void setCarBrands(List<CarBrandListDisplayModel> carBrands);
        void clearCarBrands();
    }

    interface Presenter extends VpContract.Presenter<View> {
        void refreshRequested();
        void carBrandClicked(CarBrandListDisplayModel displayModel);
    }
}
