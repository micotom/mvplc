package com.bragi.mvplc.components;

import android.support.annotation.NonNull;

public interface MvplcPresenter {

    void start(@NonNull final MvplcFragment fragment);
    void stop();

}
