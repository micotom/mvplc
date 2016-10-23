package com.bragi.mvplc.components;

interface FragmentLifecycleComponents {

    void onFragmentCreated();

    void onFragmentViewCreated();

    void onFragmentStarted();

    void onFragmentResumed();

    void onFragmentPaused();

    void onFragmentStopped();

    void onFragmentDestroyed();

}
