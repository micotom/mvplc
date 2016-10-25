package com.bragi.mvplcapp.utils;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.mvp.carbrandDetail.CarBrandDetailFragment;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandListDisplayModel;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandsFragment;

public class Navigator {

    private static final @IdRes int CONTENT_VIEW_ID = R.id.content;
    @Nullable
    private AppCompatActivity appCompatActivity;

    private CarBrandListDisplayModel selectedCarBrand;

    public void setActivity(AppCompatActivity activity) {
        appCompatActivity = activity;
        if (appCompatActivity != null) {
            updateNavigationState();
        }
    }

    public void navigateToCarbrandDetail(CarBrandListDisplayModel carBrand) {
        selectedCarBrand = carBrand;
        updateNavigationState();
    }

    public void navigateBack() {
        if (selectedCarBrand != null) {
            selectedCarBrand = null;
            updateNavigationState();
        } else {
            appCompatActivity.finish();
        }
    }

    private void updateNavigationState() {
        resetNavigationBarTitle();
        FragmentManager fm = appCompatActivity.getSupportFragmentManager();
        if (selectedCarBrand != null) {
            fm.beginTransaction().replace(CONTENT_VIEW_ID,
                    CarBrandDetailFragment.getInstance(selectedCarBrand)).commit();
        } else {
            fm.beginTransaction().replace(CONTENT_VIEW_ID, new CarBrandsFragment()).commit();
        }
    }

    private void resetNavigationBarTitle() {
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
    }
}
