# mvplc

<b>M</b>odel <b>V</b>iew <b>P</b>resenter <b>L</b>ife<b>C</b>ycle - a framework for Android applications using the ModelViewPresenter architecture approach while keeping the Presenter lifecycle agnostic. 

When a lifecycle aware component (like a Gps tracker class that shall explicitly be stopped during <i>onPause()</i>) is needed, a MvplcComponent can be attached to the according fragment. 

Both MvplcPresenter as well as MvplcComponent can be easily linked in the Fragment implementation via annotations.