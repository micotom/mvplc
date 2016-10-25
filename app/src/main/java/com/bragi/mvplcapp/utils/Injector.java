package com.bragi.mvplcapp.utils;

import com.bragi.mvplcapp.data.DataStore;
import com.bragi.mvplcapp.data.MockDataStoreImpl;

public enum Injector {
    INSTANCE();

    private final Navigator navigator;
    private final DataStore dataStore;

    Injector() {
        this.navigator = new Navigator();
        this.dataStore = new MockDataStoreImpl();
    }

    public Navigator provideNavigator() {
        return navigator;
    }

    public DataStore provideDataStore() {
        return dataStore;
    }
}
