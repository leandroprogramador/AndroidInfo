package com.pastimeapps.androidinfo.helper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class GetBluetooth {

    private Context context;

    public GetBluetooth (Context context){
        this.context = context;
    }

    public String BlueTooth(){

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return null;
        }

        String adress = mBluetoothAdapter.getAddress();
        String name = mBluetoothAdapter.getName();
        int state = mBluetoothAdapter.getState();
        String nameState;

        switch (state){
            case 10:
                nameState = "off";
                break;
            case 11:
                nameState = "Turning on";
                break;
            case 12:
                nameState = "on";
                break;
            case 13:
                nameState = "Turning off";
                break;

        }

        return null;
    }
}
