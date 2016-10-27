package com.bragi.mvplc.components;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Observable;

public abstract class FragmentLifecycleDelegate {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CREATED, VIEW_CREATED, STARTED, RESUMED, PAUSED, STOPPED, VIEW_DESTROYED, DESTROYED})
    @interface FragmentLifecycleEvent {}

    static final int CREATED = 0;
    static final int VIEW_CREATED = 1;
    static final int STARTED = 2;
    static final int RESUMED =3;
    static final int PAUSED = 4;
    static final int STOPPED = 5;
    static final int VIEW_DESTROYED = 6;
    static final int DESTROYED = 7;

    final void attach(final Observable<Integer> lifecycleObservable) {
        lifecycleObservable.subscribe(fragmentLifecycleEvent -> {
            switch (fragmentLifecycleEvent) {
                case CREATED:
                    onFragmentCreated();
                    break;
                case VIEW_CREATED:
                    onFragmentViewCreated();
                    break;
                case STARTED:
                    onFragmentStarted();
                    break;
                case RESUMED:
                    onFragmentResumed();
                    break;
                case PAUSED:
                    onFragmentPaused();
                    break;
                case STOPPED:
                    onFragmentStopped();
                    break;
                case VIEW_DESTROYED:
                    onFragmentViewDestroyed();
                    break;
                case DESTROYED:
                    onFragmentDestroyed();
                    break;
            }
        });
    }

    protected void onFragmentCreated() { /* default empty implementation */ }

    protected void onFragmentViewCreated() { /* default empty implementation */ }

    protected void onFragmentStarted() { /* default empty implementation */ }

    protected void onFragmentResumed() { /* default empty implementation */ }

    protected void onFragmentPaused() { /* default empty implementation */ }

    protected void onFragmentStopped() { /* default empty implementation */ }

    protected void onFragmentViewDestroyed() { /* default empty implementation */ }

    protected void onFragmentDestroyed() { /* default empty implementation */ }
}
