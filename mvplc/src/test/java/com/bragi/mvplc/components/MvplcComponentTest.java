package com.bragi.mvplc.components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bragi.mvplc.MvplcComponentLink;
import com.bragi.mvplc.MvplcPresenterLink;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MvplcComponentTest {

    @Test
    public void testLifecycle() {
        MvplcFragmentImpl view = new MvplcFragmentImpl();
        MvplcPresenterImpl presenter = (MvplcPresenterImpl)view.getPresenter();
        MvplcComponentImpl component = (MvplcComponentImpl)view.getLcComponent();

        view.onCreate(null);
        assertEquals(FragmentLifecycleEvent.CREATED, component.status);

        view.onCreateView(null, null, null);
        assertEquals(FragmentLifecycleEvent.VIEW_CREATED, component.status);

        assertEquals(FragmentLifecycleEvent.STARTED, presenter.status);

        view.onResume();
        assertEquals(FragmentLifecycleEvent.RESUMED, component.status);

        view.onPause();
        assertEquals(FragmentLifecycleEvent.PAUSED, component.status);

        view.onDestroyView();
        assertEquals(FragmentLifecycleEvent.DESTROYED, component.status);

        assertEquals(FragmentLifecycleEvent.STOPPED, presenter.status);

        assertTrue(view.lifecycleSubjectCompleted);
    }

    @SuppressLint("ValidFragment")
    private static class MvplcFragmentImpl extends MvplcFragment {
        @MvplcPresenterLink MvplcPresenterImpl presenter;
        @MvplcComponentLink MvplcComponentImpl lcComponent;
        @Override
        protected void onFragmentViewCreate() {}
        @Nullable
        @Override
        public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return null;
        }
        @Override
        public void onFragmentViewResumed() {}
        @Override
        public void onFragmentViewPaused() {}
        @Override
        public void onFragmentViewDestroyed() {}
    }

    private static class MvplcComponentImpl extends MvplcComponent {
        FragmentLifecycleEvent status;
        public MvplcComponentImpl(){}
        @Override
        public void onFragmentCreated() {
            status = FragmentLifecycleEvent.CREATED;
        }
        @Override
        public void onFragmentViewCreated() {
            status = FragmentLifecycleEvent.VIEW_CREATED;
        }
        @Override
        public void onFragmentStarted() {
            status = FragmentLifecycleEvent.STARTED;
        }
        @Override
        public void onFragmentResumed() {
            status = FragmentLifecycleEvent.RESUMED;
        }
        @Override
        public void onFragmentPaused() {
            status = FragmentLifecycleEvent.PAUSED;
        }
        @Override
        public void onFragmentStopped() {
            status = FragmentLifecycleEvent.STOPPED;
        }
        @Override
        public void onFragmentDestroyed() {
            status = FragmentLifecycleEvent.DESTROYED;
        }
    }

    private static class MvplcPresenterImpl implements MvplcPresenter {
        FragmentLifecycleEvent status;
        public MvplcPresenterImpl(){};
        @Override
        public void start(@NonNull MvplcFragment fragment) {
            status = FragmentLifecycleEvent.STARTED;
        }
        @Override
        public void stop() {
            status = FragmentLifecycleEvent.STOPPED;
        }
    }

}
