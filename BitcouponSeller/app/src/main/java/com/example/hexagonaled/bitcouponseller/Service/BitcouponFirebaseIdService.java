package com.example.hexagonaled.bitcouponseller.Service;

/**
 * Created by hexagonaled on 02/05/2018.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

public class BitcouponFirebaseIdService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token=FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " +token);
        sendToServer(token);
    }
    private void sendToServer(String token) {
    }
}
