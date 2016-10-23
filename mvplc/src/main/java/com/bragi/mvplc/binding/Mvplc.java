package com.bragi.mvplc.binding;

import android.support.annotation.NonNull;

import com.bragi.mvplc.MvplcComponentLink;
import com.bragi.mvplc.MvplcPresenterLink;
import com.bragi.mvplc.components.MvplcComponent;
import com.bragi.mvplc.components.MvplcFragment;
import com.bragi.mvplc.components.MvplcPresenter;
import com.bragi.mvplc.exceptions.MoreThanOnePresenterException;
import com.bragi.mvplc.exceptions.NoEmptyConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Mvplc {

    private Mvplc() {
        throw new UnsupportedOperationException();
    }

    public static <T extends MvplcFragment> void bind(@NonNull final T fragment) {
        int presenterCount = 0;
        for (Field f : fragment.getClass().getDeclaredFields()) {
            // get handle to MvplcFragment class the fragment extends from
            Class<?> baseClass = fragment.getClass().getSuperclass();
            while (baseClass != MvplcFragment.class) {
                baseClass = baseClass.getSuperclass();
            }
            if (f.getAnnotation(MvplcPresenterLink.class) != null) {
                invokePresenterSetter(fragment, baseClass, f);
                presenterCount++;
            }
            if (f.getAnnotation(MvplcComponentLink.class) != null) {
                invokeLcComponentAttacher(fragment, baseClass, f);
            }
            if (presenterCount > 1) {
                throw new MoreThanOnePresenterException();
            }
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private static void invokeLcComponentAttacher(@NonNull final MvplcFragment fragment,
                                                  final Class<?> baseClass, final Field f) {
        try {
            Method componentAttacher = baseClass.getDeclaredMethod("attachLifecycleComponent",
                    MvplcComponent.class);
            componentAttacher.setAccessible(true);
            // get constructor for MvplcComponentLink
            // TODO check if field extends MvplcComponent
            Constructor constructor = f.getType().getConstructor();
            constructor.setAccessible(true);
            // execute setting the presenter
            Object presenterInstance = constructor.newInstance();
            componentAttacher.invoke(fragment, presenterInstance);
        } catch (NoSuchMethodException e) {
            throw new NoEmptyConstructorException(MvplcComponentLink.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private static void invokePresenterSetter(@NonNull final MvplcFragment fragment,
                                              final Class<?> baseClass, final Field f) {
        try {
            // get setPresenter(MvplcPresenter) method handle
            Method presenterSetter = baseClass.getDeclaredMethod("setPresenter", MvplcPresenter.class);
            presenterSetter.setAccessible(true);
            // get constructor for MvplcPresenter
            // TODO check if field extends MvplcPresenter
            Constructor constructor = f.getType().getConstructor();
            constructor.setAccessible(true);
            // execute setting the presenter
            Object presenterInstance = constructor.newInstance();
            presenterSetter.invoke(fragment, presenterInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new NoEmptyConstructorException(MvplcPresenterLink.class);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
