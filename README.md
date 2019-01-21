[![Build Status](https://travis-ci.com/luanvotrongdev/get_wifi_info.svg?branch=master)](https://travis-ci.com/luanvotrongdev/get_wifi_info)
[![pub package](https://img.shields.io/pub/v/wifi_ip.svg)](https://pub.dartlang.org/packages/wifi_ip)

# wifi_ip

A flutter plugin to get Wifi IP info.

## Getting Started

### Pubspec
1. Add to pubspec
  ```Pubspec
  get_wifi_info:
    git:
      url: https://github.com/luanvotrongdev/get_wifi_info.git
  ```
2. run pub get

### Android
1. Add `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />` to AndroidManifest
2. Call API 
``` Dart
WifiInfo info;
try {
  info = await GetWifiInfo.getWifiInfo;
} on PlatformException {
  print('Failed to get broadcast IP.');
}
```
### iOS
1. Call API
``` Dart
WifiInfo info;
try {
  info = await GetWifiInfo.getWifiInfo;
} on PlatformException {
  print('Failed to get broadcast IP.');
}
```
### Example
``` Dart
import 'package:wifi_ip/wifi_ip.dart';

try {
  WifiIpInfo info = await WifiIp.getWifiIp;
} on PlatformException {
  print('Failed to get broadcast IP.');
}
```

## Demo
iOS | Android
------------ | -------------
![](https://media.giphy.com/media/k6r9canVzxarhfHory/giphy.gif) | ![](https://media.giphy.com/media/pjP9qoMJgL8TD7DzO2/giphy.gif)
