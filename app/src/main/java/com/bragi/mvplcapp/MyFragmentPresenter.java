package com.bragi.mvplcapp;

import android.support.annotation.NonNull;

class MyFragmentPresenter implements MyContract.Presenter {

    private @NonNull MyContract.View view;

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        // Nothing to do
    }
}
