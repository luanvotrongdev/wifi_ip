# get_wifi_info

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

## Demo
iOS | Android
------------ | -------------
![](https://media.giphy.com/media/k6r9canVzxarhfHory/giphy.gif) | ![](https://media.giphy.com/media/pjP9qoMJgL8TD7DzO2/giphy.gif)
