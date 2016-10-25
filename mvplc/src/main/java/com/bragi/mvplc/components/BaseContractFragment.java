package com.bragi.mvplc.components;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public abstract class BaseContractFragment extends Fragment implements BaseContract.View {
    private final Subject<Integer, Integer> lifecycleSubject = PublishSubject.create();

    @Override
    public final void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleDelegate.CREATED);
        onFragmentCreated(savedInstanceState);
    }

    @Override
    @Nullable
    public final View onCreateView (LayoutInflater inflater,
                                    ViewGroup container,
                                    Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleDelegate.VIEW_CREATED);
        return onCreateFragmentView(inflater, container, savedInstanceState);
    }


    @Override
    public final void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentLifecycleDelegate.STARTED);
        getPresenter().onStart();
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
        getPresenter().onStop();
        onFragmentStopped();
        super.onStop();
    }

    @Override
    public final void onDestroyView() {
        lifecycleSubject.onNext(FragmentLifecycleDelegate.VIEW_DESTROYED);
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

    protected final void attachLifecycleComponent(FragmentLifecycleDelegate component) {
        component.attach(lifecycleSubject.asObservable());
    }

    // Methods available for override by children

    @NonNull
    protected abstract BaseContract.Presenter getPresenter();

    // Optional methods
    protected void onFragmentCreated(Bundle savedInstanceState) { /* default implementation */ }
    @Nullable
    protected View onCreateFragmentView(LayoutInflater inflater,
                                              ViewGroup container,
                                              Bundle savedInstanceState) {
        /* default implementation */
        return null;
    }
    protected void onFragmentStarted() { /* default implementation */ }
    protected void onFragmentResumed() { /* default implementation */ }
    protected void onFragmentPaused() { /* default implementation */ }
    protected void onFragmentStopped() { /* default implementation */ }
    protected void onFragmentViewDestroyed() { /* default implementation */ }
    protected void onFragmentDestroyed() { /* default implementation */ }

}
