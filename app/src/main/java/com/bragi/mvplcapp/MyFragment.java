package com.bragi.mvplcapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bragi.mvplc.MvplcPresenterLink;
import com.bragi.mvplc.components.MvplcFragment;

public class MyFragment extends MvplcFragment implements MyMvplc.Fragment {

    @MvplcPresenterLink MyFragmentPresenter presenter;

    @Override
    protected void onFragmentViewCreate() {

    }

    @Nullable
    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onFragmentViewResumed() {

    }

    @Override
    public void onFragmentViewPaused() {

    }

    @Override
    public void onFragmentViewDestroyed() {

    }

    @Override
    public void writeHello() {

    }
}
