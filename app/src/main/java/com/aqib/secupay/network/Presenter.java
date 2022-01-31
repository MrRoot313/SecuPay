package com.aqib.secupay.network;

public interface Presenter<V> {

    void attachListener(V listener);

    void detachListener();

}
