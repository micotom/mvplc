package com.bragi.mvplcapp;

import com.bragi.mvplc.components.BaseContractFragment;

public class MyFragment extends BaseContractFragment<MyContract.Presenter> implements MyContract.View {

    private MyContract.Presenter presenter;

    @Override
    public void writeHello() {

    }

    @Override
    public void setPresenter(MyContract.Presenter presenter) {
        super.setPresenter(presenter);
        this.presenter = presenter;
    }
}
