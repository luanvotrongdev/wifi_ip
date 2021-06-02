import 'dart:async';

import 'package:flutter/services.dart';

class WifiIpInfo {
  final String? ip;
  final String? netmask;
  final String? broadcastIP;

  WifiIpInfo.fromMap(Map<String, String> map)
      : ip = map["ip"],
        netmask = map["netmask"],
        broadcastIP = map["broadcastIP"];
}

class WifiIp {
  static const MethodChannel _channel =
      const MethodChannel('com.lulu.plugin/get_wifi_ip');

  //
  static Future<WifiIpInfo> get getWifiIp async {
    final Map wifiInfoMap =
        Map<String, String>.from(await _channel.invokeMethod('getWifiIp'));
    return WifiIpInfo.fromMap(wifiInfoMap as Map<String, String>);
  }
}
