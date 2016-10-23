package com.bragi.mvplcapp;

import android.support.annotation.NonNull;

import com.bragi.mvplc.components.MvplcFragment;
import com.bragi.mvplc.components.MvplcPresenter;

@SuppressWarnings("WeakerAccess")
public class MyFragmentPresenter implements MvplcPresenter {

    @SuppressWarnings("FieldCanBeLocal")
    private MyFragment fragment;

    @Override
    public void start(@NonNull MvplcFragment fragment) {
        this.fragment = (MyFragment)fragment;
        this.fragment.writeHello();
    }

    @Override
    public void stop() {

    }

}
