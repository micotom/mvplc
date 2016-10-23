package com.bragi.mvplc.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bragi.mvplc.binding.Mvplc;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public abstract class MvplcFragment extends Fragment {

    private MvplcPresenter presenter;
    private MvplcComponent lcComponent; // only used for testing

    @VisibleForTesting
    // TODO move to test impl
    boolean lifecycleSubjectCompleted; // actually only used for testing

    private final Subject<FragmentLifecycleEvent, FragmentLifecycleEvent> lifecycleSubject =
            PublishSubject.create();

    public MvplcFragment() {
        Mvplc.bind(this);
    }

    @SuppressWarnings("unused")
    private void setPresenter(MvplcPresenter presenter) {
        this.presenter = presenter;
    }

    @VisibleForTesting
    MvplcPresenter getPresenter() {
        return presenter;
    }

    @SuppressWarnings("unused")
    private void attachLifecycleComponent(MvplcComponent component) {
        component.attach(this);
        this.lcComponent = component;
    }

    @VisibleForTesting
    MvplcComponent getLcComponent() {
        return lcComponent;
    }

    final Observable<FragmentLifecycleEvent> getLifecycleObservable() {
        return lifecycleSubject.asObservable();
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleEvent.CREATED);
        onFragmentViewCreate();

        lifecycleSubject.asObservable()
                .subscribe(
                        new Action1<FragmentLifecycleEvent>() {
                            @Override
                            public void call(FragmentLifecycleEvent fragmentLifecycleEvent) {

                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                lifecycleSubjectCompleted = true;
                            }
                        }
                );

    }

    protected abstract void onFragmentViewCreate();

    @Override
    @Nullable
    public final View onCreateView (LayoutInflater inflater,
                                    ViewGroup container,
                                    Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        lifecycleSubject.onNext(FragmentLifecycleEvent.VIEW_CREATED);
        presenter.start(this);
        return onCreateFragmentView(inflater, container, savedInstanceState);
    }

    @Nullable
    public abstract View onCreateFragmentView(LayoutInflater inflater,
                                              ViewGroup container,
                                              Bundle savedInstanceState);

    @Override
    public final void onResume() {
        lifecycleSubject.onNext(FragmentLifecycleEvent.RESUMED);
        onFragmentViewResumed();
        super.onResume();
    }

    public abstract void onFragmentViewResumed();

    @Override
    public final void onPause() {
        lifecycleSubject.onNext(FragmentLifecycleEvent.PAUSED);
        onFragmentViewPaused();
        super.onPause();
    }

    public abstract void onFragmentViewPaused();

    @Override
    public final void onDestroyView() {
        presenter.stop();
        lifecycleSubject.onNext(FragmentLifecycleEvent.DESTROYED);
        onFragmentViewDestroyed();
        lifecycleSubject.onCompleted();
        super.onDestroyView();
    }

    public abstract void onFragmentViewDestroyed();

}
