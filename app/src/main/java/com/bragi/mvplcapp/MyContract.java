package com.bragi.mvplcapp;

import com.bragi.mvplc.components.BaseContract;

interface MyContract {

    interface View extends BaseContract.View<Presenter> {
        void writeHello();
    }

    interface Presenter extends BaseContract.Presenter {

    }

}
