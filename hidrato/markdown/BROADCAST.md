# [Broadcast Receiver](https://developer.android.com/guide/components/broadcasts)

## Overview

- Core Android Component that enables applications to receive intents
    - that are broadcast by the system or other applications
- Can be triggered even when the app is not running
    - i.e. you have an app that needs to change its UI
    - depending on if device is online vs offline
    - broadcast receiver is what you would use to be triggered when changes occur
    - specify which request receiver is interested in via intent filter
    - intent filters are commonly used in app's manifest file
- System broadcast intents:
    - android.intent.action.SCREEN_ON
    - android.intent.action.MEDIA_MOUNTED
    - android.intent.action.HEADSET_PLUG
    - android.intent.action.BATTERY_LOW
    - android.intent.action.DOWNLOAD_COMPLETE
    - android.media.AUDIO_BECOMING_NOISY

