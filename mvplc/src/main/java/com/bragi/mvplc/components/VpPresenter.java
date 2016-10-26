package com.bragi.mvplc.components;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class VpPresenter<V extends VpContract.View> implements VpContract.Presenter<V> {

    private CompositeSubscription subscriptions;

    protected final void addSubscription(final @NonNull Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    final void cleanupSubscriptions() {
        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
        subscriptions = null;
    }
}
