package com.bragi.mvplcapp.mvp.carbrandDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bragi.mvplc.components.VpFragment;
import com.bragi.mvplc.components.VpPresenter;
import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.utils.Injector;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarBrandDetailFragment extends VpFragment<CarBrandDetailContract.View>
        implements CarBrandDetailContract.View {

    private static final String ARG_SELECTED_CARBRAND_ID = "selected_carbrand_id";

    public static CarBrandDetailFragment getInstance(long carBrandId) {
        CarBrandDetailFragment fragment = new CarBrandDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putLong(ARG_SELECTED_CARBRAND_ID, carBrandId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @BindView(R.id.logoImageView)
    ImageView logoImageView;
    @BindView(R.id.countryOriginTextView)
    TextView countryOriginTextView;
    @BindView(R.id.founderNamesTextView)
    TextView founderNamesTextView;
    @BindView(R.id.detailProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.contentScrollView)
    ScrollView contentScrollView;

    @NonNull
    @Override
    protected VpPresenter<CarBrandDetailContract.View> createPresenter() {
        return new CarBrandDetailPresenter(
                getArguments().getLong(ARG_SELECTED_CARBRAND_ID, -1),
                Injector.INSTANCE.provideDataStore()
        );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_brand_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        contentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCarBrand(CarBrandDetailDisplayModel carBrand) {
        countryOriginTextView.setText(carBrand.countryName);
        founderNamesTextView.setText(carBrand.foundersNames);
        Picasso.with(getContext()).load(carBrand.logoImageUrl).placeholder(R.drawable.loading_placeholder).into(logoImageView);
    }
}
