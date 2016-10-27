package com.bragi.mvplcapp.utils;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.mvp.carbrandDetail.CarBrandDetailFragment;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandsFragment;

public class Navigator {

    private static final @IdRes int CONTENT_VIEW_ID = R.id.content;
    @Nullable
    private AppCompatActivity appCompatActivity;

    private Long selectedCarBrandId;

    public void setActivity(AppCompatActivity activity) {
        appCompatActivity = activity;
        if (appCompatActivity != null) {
            updateNavigationState();
        }
    }

    public void navigateToCarbrandDetail(long carBrandId) {
        selectedCarBrandId = carBrandId;
        updateNavigationState();
    }

    public void navigateBack() {
        if (selectedCarBrandId != null) {
            selectedCarBrandId = null;
            updateNavigationState();
        } else {
            if (appCompatActivity != null) {
                appCompatActivity.finish();
            }
        }
    }

    private void updateNavigationState() {
        if (appCompatActivity == null) {
            return;
        }

        FragmentManager fm = appCompatActivity.getSupportFragmentManager();
        if (selectedCarBrandId != null) {
            fm.beginTransaction().replace(CONTENT_VIEW_ID,
                    CarBrandDetailFragment.getInstance(selectedCarBrandId)).commit();
        } else {
            fm.beginTransaction().replace(CONTENT_VIEW_ID, new CarBrandsFragment()).commit();
        }
    }
}
