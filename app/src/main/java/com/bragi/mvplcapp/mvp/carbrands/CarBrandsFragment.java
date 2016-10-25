package com.bragi.mvplcapp.mvp.carbrands;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bragi.mvplc.components.BaseContractFragment;
import com.bragi.mvplcapp.R;
import com.bragi.mvplcapp.utils.Injector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarBrandsFragment extends BaseContractFragment<CarBrandsContract.View, CarBrandsContract.Presenter>
        implements CarBrandsContract.View, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.listSwipeContainer)
    SwipeRefreshLayout swipeRefreshContainer;

    private CarBrandsContract.Presenter presenter;
    private ArrayAdapter<CarBrandListDisplayModel> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_brands, container, false);
        ButterKnife.bind(this, rootView);
        adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        swipeRefreshContainer.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void showProgress() {
        if (!swipeRefreshContainer.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshContainer.setRefreshing(false);
    }

    @Override
    public void setCarBrands(List<CarBrandListDisplayModel> carBrands) {
        adapter.clear();
        adapter.addAll(carBrands);
    }

    @Override
    public void clearCarBrands() {
        adapter.clear();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.carBrandClicked(adapter.getItem(position));
    }

    @Override
    public void onRefresh() {
        presenter.refreshRequested();
    }

    @NonNull
    @Override
    protected CarBrandsContract.Presenter createPresenter() {
        presenter = new CarBrandsPresenter(Injector.INSTANCE.provideDataStore(),
                Injector.INSTANCE.provideNavigator());
        return presenter;
    }
}
