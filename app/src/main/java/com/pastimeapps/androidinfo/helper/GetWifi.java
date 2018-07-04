package com.pastimeapps.androidinfo.helper;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.pastimeapps.androidinfo.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

public class GetWifi {

    private Context context;

    public GetWifi(Context context){
        this.context=context;
    }

    public String Wifi(){
        String strWifiInfo = "";

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();

        int ipAddress = connectionInfo.getIpAddress();
        String ipString = String.format("%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));

        final int NumOfRSSILevels = 5;

        String SSID = connectionInfo.getSSID();
        String MacAdress =  connectionInfo.getMacAddress();
        int Frequence = connectionInfo.getFrequency();
        int LinkSpeed = connectionInfo.getLinkSpeed();
        int Rssi = connectionInfo.getRssi();
        int SignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), NumOfRSSILevels);
        int SignalLevelMax = NumOfRSSILevels;

        strWifiInfo +=
                        "SSID:" + SSID + "\n" +
                          context.getText(R.string.ip_address)   + ipString + "\n" +
                          context.getText(R.string.mac_address)  + MacAdress  +  "\n" +
                          context.getText(R.string.frequence)  +Frequence +  WifiInfo.FREQUENCY_UNITS + "\n" +
                          context.getText(R.string.link_speed) +LinkSpeed +   WifiInfo.LINK_SPEED_UNITS + "\n" +
                        "Rssi: " + Rssi + "dBm" + "\n" +
                        "Rssi Level: " +
                        SignalLevel +
                        " of " + SignalLevelMax;
        return strWifiInfo;
    }
}
