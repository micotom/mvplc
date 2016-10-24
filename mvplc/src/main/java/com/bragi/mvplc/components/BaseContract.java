package com.bragi.mvplc.components;

public interface BaseContract {

    interface View<P extends Presenter> {
        void setPresenter(P presenter);
    }

    interface Presenter {
        void start();
        void stop();
    }
}
