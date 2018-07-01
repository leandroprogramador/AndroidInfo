package com.pastimeapps.androidinfo;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.pastimeapps.androidinfo.helper.GetBluetooth;
import com.pastimeapps.androidinfo.helper.GetPhone;
import com.pastimeapps.androidinfo.helper.GetWifi;
import com.pastimeapps.androidinfo.helper.RequestPermissionHandler;

public class MainActivity extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;

    TextView imei, wifiInfo, line, bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestPermissionHandler = new RequestPermissionHandler();

        init();
        showPermmission();
    }

    public void init(){
        imei = findViewById(R.id.imei);
        wifiInfo = findViewById(R.id.wifiInfo);
        line = findViewById(R.id.line);
        bluetooth = findViewById(R.id.bluetooth);
    }

    private void showPermmission(){
        mRequestPermissionHandler.requestPermission(this, new String[] {
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 123, new RequestPermissionHandler.RequestPermissionListener() {
            @Override
            public void onSuccess() {
                GetInfoAndroid();
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, getText(R.string.request_permission_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }

    public void GetInfoAndroid(){
        GetPhone getPhone = new GetPhone(this);
        imei.setText(getPhone.Imei());
        line.setText(getPhone.LineNumber());

        GetWifi getWifi = new GetWifi(this);
        wifiInfo.setText(getWifi.Wifi());

        GetBluetooth getBluetooth = new GetBluetooth(this);
        bluetooth.setText(getBluetooth.BlueTooth());
    }

}

