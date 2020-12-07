# [BroadcastReceiver](https://developer.android.com/guide/components/broadcasts)

## System Intents (State)

- Core Android Component that enables applications to receive intents
    - that are broadcast by the system or other applications
- Can be triggered even when the app is not running
    - i.e. you have an app that needs to change its UI
    - depending on if device is online vs offline
    - broadcast receiver is what you would use to be triggered when changes occur
    - specify which request receiver is interested in via intent filter
    - intent filters are commonly used in app manifest file
- System broadcast intents:
    - android.intent.action.SCREEN_ON
    - android.intent.action.MEDIA_MOUNTED
    - android.intent.action.HEADSET_PLUG
    - android.intent.action.BATTERY_LOW
    - android.intent.action.DOWNLOAD_COMPLETE
    - android.media.AUDIO_BECOMING_NOISY
- App that needs to know about one of these state changes on device
  - and run some code, you will be using a BroadcastReceiver
- IntentFilter
  - expression that says what intents should trigger component
  - not specific to BroadcastReceivers

## Static BroadcastReceiver

- Triggered whenever broadcast intent occurs
- Receiver will be triggered even when app is offline
- Anti-Pattern:
  - 10 apps downloaded all using this static BroadcastReceiver
  - all apps would be triggered on defined intent
  - device would slow to a crawl
  - some broadcast intents do not allow static BroadcastReceiver
    - i.e. FLAG_RECEIVER_REGISTERED_ONLY

## Dynamic BroadcastReceiver

- Tied to app lifecycle
- Typically prefer dynamic over static (or Job Scheduling)

## Test Simulation

- adb shell dumpsys battery reset (plug in phone)
- adb shell dumpsys battery unplug (unplug phone)

## Battery State Bug

- App currently adds/removes dynamic broadcast receiver in onResume and onPause
- When app is not visible the plug image will not update
- TODO: get current state of battery
  - when app resumes update image accordingly

