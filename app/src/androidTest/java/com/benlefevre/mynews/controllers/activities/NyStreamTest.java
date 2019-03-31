package com.benlefevre.mynews.controllers.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

@RunWith(AndroidJUnit4.class)
public class NyStreamTest {

    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

//    Test if the device has a network access
    @Test
    public void internetConnectivityTest() {
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Assert.assertTrue(isConnected);
    }
}
