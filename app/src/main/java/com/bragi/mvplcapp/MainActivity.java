package com.bragi.mvplcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bragi.mvplcapp.utils.Injector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.INSTANCE.provideNavigator().setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injector.INSTANCE.provideNavigator().setActivity(null);
    }
}
