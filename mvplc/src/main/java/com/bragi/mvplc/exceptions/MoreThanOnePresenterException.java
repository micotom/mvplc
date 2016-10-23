package com.bragi.mvplc.exceptions;

public class MoreThanOnePresenterException extends RuntimeException {

    public MoreThanOnePresenterException() {
        super("MvplcFragments can only have one presenter annotated with @MvplcPresenterLink.");
    }

}
