package com.bragi.mvplc.components;

import rx.functions.Action1;

public abstract class MvplcComponent implements FragmentLifecycleComponents {

    final void attach(final MvplcFragment view) {
        view.getLifecycleObservable().subscribe(new Action1<FragmentLifecycleEvent>() {
            @Override
            public void call(FragmentLifecycleEvent fragmentLifecycleEvent) {
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
                    case DESTROYED:
                        onFragmentDestroyed();
                        break;
                }
            }
        });
    }

}
