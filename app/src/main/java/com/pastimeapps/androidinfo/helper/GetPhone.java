package com.pastimeapps.androidinfo.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

public class GetPhone {

    private Context context;
    TelephonyManager telephonyManager;


    public GetPhone(Context context) {
        this.context = context;
    }


    public String Imei() {

        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        return telephonyManager.getDeviceId();
    }

    public String LineNumber() {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        String line1Number = telephonyManager.getLine1Number();
        return line1Number .equals("") ? "(00) 00000-0000" : line1Number;

    }
}
