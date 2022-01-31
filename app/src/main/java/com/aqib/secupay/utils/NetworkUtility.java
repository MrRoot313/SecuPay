package com.aqib.secupay.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtility {

    private static final String TAG = "UtilityNetwork";

    public static boolean isNetworkAvailable(final Context context) {

        boolean available = false;
        try {

            final ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                final NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (final NetworkInfo element : info) {
                        if (element.getState() == NetworkInfo.State.CONNECTED) {
                            available = true;
                        }
                    }
                }
            }
            if (available == false) {
                final NetworkInfo wiMax = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);

                if (wiMax != null && wiMax.isConnected()) {
                    available = true;
                }
            }
        } catch (final NullPointerException exception) {
            DebugHelper.trackException(NetworkUtility.TAG, exception);
        }

        return available;
    }

    public static void callIntentWithFlags(Context c, Class<?> cls, boolean flags) {
        Intent intent = new Intent(c, cls);
        if (flags) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        c.startActivity(intent);
    }
}
