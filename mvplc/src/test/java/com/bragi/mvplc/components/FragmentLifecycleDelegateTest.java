package com.bragi.mvplc.components;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.support.annotation.NonNull;

import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;

public class FragmentLifecycleDelegateTest {

    @Test
    public void testLifecycle() {
        VpFragmentImpl view = new VpFragmentImpl();

        // fake a loader manager so that onStart and onDestroy do not crash
        try {
            Field f = view.getClass().getSuperclass().getSuperclass().getDeclaredField("mCheckedForLoaderManager");
            f.setAccessible(true);
            f.set(view, true);

            f = view.getClass().getSuperclass().getSuperclass().getDeclaredField("mLoaderManager");
            f.setAccessible(true);
            f.set(view, Mockito.mock(LoaderManager.class));
        } catch (Exception e) {

        }

        MvplcPresenterImpl presenter = new MvplcPresenterImpl();
        FragmentLifecycleDelegateImpl component = new FragmentLifecycleDelegateImpl();
        view.setPresenter(presenter);
        view.attachLifecycleDelegate(component);

        view.onCreate(null);
        assertEquals(FragmentLifecycleDelegate.CREATED, component.status);

        view.onViewCreated(null, null);
        assertEquals(FragmentLifecycleDelegate.VIEW_CREATED, component.status);
        assertEquals(FragmentLifecycleDelegate.STARTED, presenter.status);

        view.onStart();
        assertEquals(FragmentLifecycleDelegate.STARTED, component.status);

        view.onResume();
        assertEquals(FragmentLifecycleDelegate.RESUMED, component.status);

        view.onPause();
        assertEquals(FragmentLifecycleDelegate.PAUSED, component.status);

        view.onStop();
        assertEquals(FragmentLifecycleDelegate.STOPPED, component.status);

        view.onDestroyView();
        assertEquals(FragmentLifecycleDelegate.VIEW_DESTROYED, component.status);
        assertEquals(FragmentLifecycleDelegate.STOPPED, presenter.status);

        view.onDestroy();
        assertEquals(FragmentLifecycleDelegate.DESTROYED, component.status);

//        assertTrue(view.lifecycleSubjectCompleted);
    }

    @SuppressLint("ValidFragment")
    private static class VpFragmentImpl extends VpFragment<VpContract.View> {
        private VpPresenter<VpContract.View> presenter;

        void setPresenter(VpPresenter<VpContract.View> presenter) {
            this.presenter = presenter;
        }

        @NonNull
        @Override
        protected VpPresenter<VpContract.View> createPresenter() {
            return presenter;
        }
    }

    private static class FragmentLifecycleDelegateImpl extends FragmentLifecycleDelegate {
        @FragmentLifecycleEvent int status;
        public FragmentLifecycleDelegateImpl(){}
        @Override
        public void onFragmentCreated() {
            status = FragmentLifecycleDelegate.CREATED;
        }
        @Override
        public void onFragmentViewCreated() {
            status = FragmentLifecycleDelegate.VIEW_CREATED;
        }
        @Override
        public void onFragmentStarted() {
            status = FragmentLifecycleDelegate.STARTED;
        }
        @Override
        public void onFragmentResumed() {
            status = FragmentLifecycleDelegate.RESUMED;
        }
        @Override
        public void onFragmentPaused() {
            status = FragmentLifecycleDelegate.PAUSED;
        }
        @Override
        public void onFragmentStopped() {
            status = FragmentLifecycleDelegate.STOPPED;
        }
        @Override
        protected void onFragmentViewDestroyed() {
            status = FragmentLifecycleDelegate.VIEW_DESTROYED;
        }
        @Override
        public void onFragmentDestroyed() {
            status = FragmentLifecycleDelegate.DESTROYED;
        }

    }

    private static class MvplcPresenterImpl extends VpPresenter<VpContract.View> implements VpContract.Presenter<VpContract.View> {
        @FragmentLifecycleDelegate.FragmentLifecycleEvent int status;
        public MvplcPresenterImpl(){};

        @Override
        public void onStart(VpContract.View view) {
            status = FragmentLifecycleDelegate.STARTED;
        }

        @Override
        public void onStop() {
            status = FragmentLifecycleDelegate.STOPPED;
        }
    }
}
