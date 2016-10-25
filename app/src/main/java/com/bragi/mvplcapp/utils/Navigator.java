package com.bragi.mvplcapp.utils;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandListDisplayModel;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandsFragment;

public class Navigator {

    private static final @IdRes int CONTENT_VIEW_ID = R.id.content;
    @Nullable
    private AppCompatActivity appCompatActivity;

    private long carBrandId = -1;

    public void setActivity(AppCompatActivity activity) {
        appCompatActivity = activity;
        if (appCompatActivity != null) {
            updateNavigationState();
        }
    }

    public void navigateToCarbrandDetail(CarBrandListDisplayModel carBrand) {
        carBrandId = carBrand.id;
        updateNavigationState();
    }

    public void navigateBack() {
        if (carBrandId > 0) {
            carBrandId = -1;
            updateNavigationState();
        } else {
            appCompatActivity.finish();
        }
    }

    private void updateNavigationState() {
        if (carBrandId > 0) {
            //TODO: detail
        } else {
            FragmentManager fm = appCompatActivity.getSupportFragmentManager();
            fm.beginTransaction().replace(CONTENT_VIEW_ID, new CarBrandsFragment()).commit();
        }
    }
}
