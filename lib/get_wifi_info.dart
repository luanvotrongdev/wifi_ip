import 'dart:async';

import 'package:flutter/services.dart';

class WifiInfo {
  final String ip;
  final String netmask;
  final String broadcastIP;

  WifiInfo.fromMap(Map<String, String> map)
      : ip = map["ip"],
        netmask = map["netmask"],
        broadcastIP = map["broadcastIP"];
}

class GetWifiInfo {
  static const MethodChannel _channel =
  const MethodChannel('com.lulu.plugin/get_wifi_info');

  //
  static Future<WifiInfo> get getWifiInfo async {
    final Map wifiInfoMap = Map<String, String>.from(await _channel.invokeMethod('getWifiInfo'));
    return WifiInfo.fromMap(wifiInfoMap);
  }
}
