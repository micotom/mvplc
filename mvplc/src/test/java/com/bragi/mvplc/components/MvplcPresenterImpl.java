package com.bragi.mvplc.components;

import android.support.annotation.NonNull;

class MvplcPresenterImpl implements MvplcPresenter {
    FragmentLifecycleEvent status;
    public MvplcPresenterImpl(){};
    @Override
    public void start(@NonNull MvplcFragment fragment) {
        status = FragmentLifecycleEvent.STARTED;
    }
    @Override
    public void stop() {
        status = FragmentLifecycleEvent.STOPPED;
    }
}
