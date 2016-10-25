package com.bragi.mvplc.components;

public interface BaseContract {

    interface View {}

    interface Presenter<V extends View> {
        void onStart(V view);
        void onStop();
    }
}
