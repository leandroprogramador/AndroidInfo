package com.pastimeapps.androidinfo.helper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;

import com.pastimeapps.androidinfo.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GetBluetooth {

    private Context context;

    public GetBluetooth (Context context){
        this.context = context;
    }

    public String BlueTooth(){

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return context.getText(R.string.bluetooth_not_found).toString();
        }

        String adress = getBluetoothMacAddress();
        String name = mBluetoothAdapter.getName();
        int state = mBluetoothAdapter.getState();
        String nameState = null;

        switch (state){
            case 10:
                nameState = context.getText(R.string.off).toString();
                break;
            case 11:
                nameState = context.getText(R.string.turning_on).toString();
                break;
            case 12:
                nameState = context.getText(R.string.on).toString();
                break;
            case 13:
                nameState = context.getText(R.string.turning_off).toString();
                break;

        }

        return  context.getText(R.string.mac_address)  + adress + "\n" +
                context.getText(R.string.name) + name + "\n" +
                context.getText(R.string.state)  + nameState + "\n";
    }

    private String getBluetoothMacAddress() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String bluetoothMacAddress = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            try {
                Field mServiceField = bluetoothAdapter.getClass().getDeclaredField("mService");
                mServiceField.setAccessible(true);

                Object btManagerService = mServiceField.get(bluetoothAdapter);

                if (btManagerService != null) {
                    bluetoothMacAddress = (String) btManagerService.getClass().getMethod("getAddress").invoke(btManagerService);
                }
            } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                Log.i("error blue", "getBluetoothMacAddress: errorblue" + e);
            }
        } else {
            bluetoothMacAddress = bluetoothAdapter.getAddress();
        }
        return bluetoothMacAddress;
    }

}
