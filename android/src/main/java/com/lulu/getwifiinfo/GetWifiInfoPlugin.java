package com.lulu.getwifiinfo;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import  java.util.HashMap;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static android.content.Context.WIFI_SERVICE;

/** GetWifiInfoPlugin */
public class GetWifiInfoPlugin implements MethodCallHandler {
  final String TAG= "GetWifiInfoPlugin";
  final Context context;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.lulu.plugin/get_wifi_info");
    channel.setMethodCallHandler(new GetWifiInfoPlugin(registrar.context()));
  }

  GetWifiInfoPlugin(Context ctx)
  {
    context = ctx;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getWifiInfo")) {
      result.success(getWifiIP());
    } else {
      result.notImplemented();
    }
  }

  public HashMap<String, String> getWifiIP() {
    HashMap<String, String> result;
    try {
      WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
      DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();

      result = new HashMap<>();
      result.put("ip", intToIp(dhcpInfo.ipAddress));
      result.put("netmask", intToIp(dhcpInfo.netmask));
      result.put("broadcastIP", intToIp((dhcpInfo.ipAddress & dhcpInfo.netmask) | ~dhcpInfo.netmask));
      return result;
    } catch (Exception e) {
      Log.e(TAG, e.getMessage());
    }
    return null;
  }

  String intToIp(int i) {
    return (i & 0xFF) + "." +
            ((i >> 8) & 0xFF) + "." +
            ((i >> 16) & 0xFF) + "." +
            ((i >> 24) & 0xFF);
  }
}
