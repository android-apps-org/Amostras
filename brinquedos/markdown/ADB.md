# [Android Debug Bridge](https://developer.android.com/studio/command-line/adb)

## Overview

- Can interact/debug amy apps on device (physical or virtual)
- Pushing/pulling files
- Viewing logcat outputs
- Running remote shell

## Basic Commands

- adb devices
- adb install helloWorld.apk
- adb -s emulator-5555 install helloWorld.apk
- adb shell dumpsys battery set usb 0
    - simulate the phone being unplugged
- adb shell dumpsys battery unplug
    - for device Android 6.0 or higher
- adb shell dumpsys battery reset
    - to ` plug ` phone back in
- adb shell am start -n com.package.name/com.package.name.ActivityName
- adb shell pm list permissions -d -g
- adb shell pm \[grant|revoke\] \<permission-name\> ...
- adb shell am make-uid-idle <package>  (runs app in background)
- adb shell cmd appops set <package> RUN_IN_BACKGROUND deny  (force background limitations)
- adb shell cmd appops set <package> RUN_IN_BACKGROUND allow  (returns app to normal behavior based on target SDK)
- adb shell dumpsys battery reset (plug in phone)
- adb shell dumpsys battery unplug (unplug phone)

## Resources

- [Battery State](https://classroom.udacity.com/nanodegrees/nd801/parts/8fca60aa-f162-4f12-abdb-e66130f2b4bf/modules/66479120-2e3e-47f5-a06b-e67b96f56392/lessons/f5ef4e52-c485-4c85-a26a-3231c17d6154/concepts/8c074bea-0ad1-46aa-ae51-b41b8e1943d3)

