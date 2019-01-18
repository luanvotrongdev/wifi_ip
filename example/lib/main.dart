import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:get_wifi_info/get_wifi_info.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  WifiInfo _result;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    // Platform messages may fail, so we use a try/catch PlatformException.
    WifiInfo info;
    try {
      info = await GetWifiInfo.getWifiInfo;
    } on PlatformException {
      print('Failed to get broadcast IP.');
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _result = info;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: _result == null ?
          Text("Getting Wifi Info") :
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("IP: ${_result.ip}"),
              Text("Netmask: ${_result.netmask}"),
              Text("Broadcast IP: ${_result.broadcastIP}"),
            ],
          ),
        ),
      ),
    );
  }
}