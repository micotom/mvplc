package com.bragi.mvplc.components;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public abstract class VpFragment<V extends VpContract.View>
        extends Fragment implements VpContract.View {

    private final Subject<Integer, Integer> lifecycleSubject = PublishSubject.create();
    private VpPresenter<V> basePresenter;

    @Override
    public final void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleDelegate.CREATED);
        onFragmentCreated(savedInstanceState);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleDelegate.VIEW_CREATED);
        onFragmentViewCreated(view, savedInstanceState);
        basePresenter = createPresenter();
        basePresenter.onStart((V) this);
    }

    @Override
    public final void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentLifecycleDelegate.STARTED);
        onFragmentStarted();
    }


    @Override
    public final void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentLifecycleDelegate.RESUMED);
        onFragmentResumed();
    }


    @Override
    public final void onPause() {
        lifecycleSubject.onNext(FragmentLifecycleDelegate.PAUSED);
        onFragmentPaused();
        super.onPause();
    }

    @Override
    public final void onStop() {
        lifecycleSubject.onNext(FragmentLifecycleDelegate.STOPPED);
        onFragmentStopped();
        super.onStop();
    }

    @Override
    public final void onDestroyView() {
        lifecycleSubject.onNext(FragmentLifecycleDelegate.VIEW_DESTROYED);
        basePresenter.cleanupSubscriptions();
        basePresenter.onStop();
        basePresenter = null;
        onFragmentViewDestroyed();
        super.onDestroyView();
    }

    @Override
    public final void onDestroy() {
        lifecycleSubject.onNext(FragmentLifecycleDelegate.DESTROYED);
        lifecycleSubject.onCompleted();
        onFragmentDestroyed();
        super.onDestroy();
    }

    protected final void attachLifecycleDelegate(FragmentLifecycleDelegate component) {
        component.attach(lifecycleSubject.asObservable());
    }

    // Methods available for override by children

    // Mandatory
    @NonNull
    protected abstract VpPresenter<V> createPresenter();

    // Optional methods
    protected void onFragmentCreated(Bundle savedInstanceState) { /* default implementation */ }
    protected void onFragmentViewCreated(View view, @Nullable Bundle savedInstanceState) { /* default implementation */ }
    protected void onFragmentStarted() { /* default implementation */ }
    protected void onFragmentResumed() { /* default implementation */ }
    protected void onFragmentPaused() { /* default implementation */ }
    protected void onFragmentStopped() { /* default implementation */ }
    protected void onFragmentViewDestroyed() { /* default implementation */ }
    protected void onFragmentDestroyed() { /* default implementation */ }

}
