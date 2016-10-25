package com.bragi.mvplcapp.mvp.carbrands;

import com.bragi.mvplc.components.BaseContract;

import java.util.List;

interface CarBrandsContract {
    interface View extends BaseContract.View {
        void showProgress();
        void hideProgress();
        void setCarBrands(List<CarBrandListDisplayModel> carBrands);
        void clearCarBrands();
    }

    interface Presenter extends BaseContract.Presenter {
        void refreshRequested();
        void carBrandClicked(CarBrandListDisplayModel displayModel);
    }
}
