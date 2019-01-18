package com.lulu.flutterwifiinfo;

import android.content.Context;
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

/** GetWifiInfoPlugin */
public class GetWifiInfoPlugin implements MethodCallHandler {
  final String TAG= "GetWifiInfoPlugin";
  final Context context;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.lulu.plugin/get_wifi_info");
    channel.setMethodCallHandler(new GetWifiInfoPlugin(registrar.context));
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
    System.setProperty("java.net.preferIPv4Stack", "true");
    try
    {
      for (Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces(); niEnum.hasMoreElements();) {
        NetworkInterface ni = niEnum.nextElement();
        if (!ni.isLoopback()) {
          for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
            result = new HashMap<>();
            result.put("ip", interfaceAddress.getAddress().toString());
            result.put("netmask", interfaceAddress.toString());
            result.put("broadcastIP", interfaceAddress.getBroadcast().toString().substring(1));
            return result;
          }
        }
      }
    }
    catch (Exception e)
    {
      Log.e(TAG, e.getMessage());
    }
    return null;
  }
}
