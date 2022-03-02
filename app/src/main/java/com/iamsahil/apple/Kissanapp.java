package com.iamsahil.apple;

import android.app.Application;

import com.onesignal.OneSignal;

public class Kissanapp extends Application {


    private static final String ONESIGNAL_APP_ID = "07408f35-8b23-402c-8ede-198686fdcdee";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }


}
