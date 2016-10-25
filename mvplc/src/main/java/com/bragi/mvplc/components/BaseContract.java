package com.bragi.mvplc.components;

public interface BaseContract {

    interface View {}

    interface Presenter {
        void onStart();
        void onStop();
    }
}
