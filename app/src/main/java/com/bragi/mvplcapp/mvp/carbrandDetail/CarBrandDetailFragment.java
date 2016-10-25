package com.bragi.mvplcapp.mvp.carbrandDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bragi.mvplc.components.BaseContractFragment;
import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.mvp.carbrands.CarBrandListDisplayModel;
import com.bragi.mvplcapp.utils.Injector;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarBrandDetailFragment extends BaseContractFragment<CarBrandDetailContract.Presenter>
        implements CarBrandDetailContract.View {

    private static final String ARG_CARBRAND_LIST_MODEL = "carbrand_list_model";

    public static CarBrandDetailFragment getInstance(CarBrandListDisplayModel carBrandListDisplayModel) {
        CarBrandDetailFragment fragment = new CarBrandDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_CARBRAND_LIST_MODEL, carBrandListDisplayModel);
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

    private CarBrandDetailContract.Presenter presenter;

    @Override
    protected void onFragmentCreated(Bundle savedInstanceState) {
        super.onFragmentCreated(savedInstanceState);
        new CarBrandDetailPresenter(getArguments().getParcelable(ARG_CARBRAND_LIST_MODEL),
                this, Injector.INSTANCE.provideDataStore());
    }

    @Nullable
    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        setTitle(carBrand.name);
        countryOriginTextView.setText(carBrand.countryName);
        founderNamesTextView.setText(carBrand.foundersNames);
        Picasso.with(getContext()).load(carBrand.logoImageUrl).placeholder(R.drawable.loading_placeholder).into(logoImageView);
    }

    @Override
    public void setCarBrandName(String carBrandName) {
        setTitle(carBrandName);
    }

    @Override
    public void setPresenter(CarBrandDetailContract.Presenter presenter) {
        super.setPresenter(presenter);
        this.presenter = presenter;
    }

    private void setTitle(@Nullable String title) {
        if (getActivity() != null) {
            getActivity().setTitle(
                    title == null ?
                    getResources().getString(R.string.title_activity_car_brand_detail) :
                    title);
        }
    }
}
