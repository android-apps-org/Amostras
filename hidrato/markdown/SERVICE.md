# [Service](https://developer.android.com/guide/components/services)

## Overview

- Meant for long running background tasks
- Great for loading and processing data in background
- App Examples: EmailService, TextService, CalendarService
    - services running on phone listening for and downloading data
    - even when these Activities are closed
- Loader: background task is loading data that is only used in Activity
    - i.e. decoding image to use in ImageView
    - crud-ing database used to populate recycler view adapter
    - some network transactions: app is inherently real-time
        - fetch data as needed in UI rather thn cache data in database
- Prefer service when task is decoupled from UI
    - i.e. updating database in background
- Services hangout in background, processing, downloading, or uploading data
    - while phone is locked
    - or user is using unrelated apps
- Starting Service: manually, schedule, bind
- Job Service: JobScheduler or Firebase JobDispatcher
- Bind Service: to get communication back (i.e. media player app)
- Service can be both Bound and Started

## Started Services

- [IntentService](https://developer.android.com/reference/android/app/IntentService)
- [JobIntentService](https://developer.android.com/reference/androidx/core/app/JobIntentService)
- [Deprecated IntentService](https://stackoverflow.com/questions/62138507/intentservice-is-deprecated-how-do-i-replace-it-with-jobintentservice)
- [JobIntentService on Linda](https://www.lynda.com/Android-tutorials/Upgrade-IntentService-JobIntentService/2833069/2319385-4.html)

## Bound Services

