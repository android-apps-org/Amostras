# Android Layout

## Android Aps

- Collections of connected components
  - that work with each other
  - and with the Android Framework
- AndroidManifest: where components are registered

## App Components

### [Activity](https://developer.android.com/reference/android/app/Activity)
- responsible for most app user interaction
- single focused thing that a user can do
- responsible for creating the window that your application uses
  - to draw and receive events from the system
- creates views to show user information and to let user interact with activity
- from user perspective an app looks like a series of linked activities
- system maintains activities on a stack to help preserve context for users

### [Service](https://developer.android.com/guide/components/services)

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
- Job Service: to communicate back to ?
- Bind Service: to get communication back (i.e. media player app)
- Service can be both Bound and Started

### [Broadcast Receivers](https://developer.android.com/guide/components/broadcasts)

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

### [Content Provider](https://developer.android.com/guide/topics/providers/content-providers)

- Sits between application and its data source
    - provide easily managed access to underlying data source
- Why another extra layer of abstraction:
    - Can change underlying data source without touching app code that access content provider
    - Some Android classes (i.e. loaders, cursor adapters) use content providers
        i.e. to use loader, need to make data accessible through a content provider
    - Open up data source to other app developers so they can access, use, and modify it
- Without content providers, this data would be limited to just your app
- When other apps use content providers to access your data,
    - its done in a secure and managed way
    - content provider is a single class through which all data requests can be made
    - so you can add code to that class to sanitize and protect your data as needed
- Under the hood there is a SQLite database
    - content provider, provides managed access to database
- [Content Resolver Query](https://developer.android.com/guide/topics/providers/content-provider-basics.html#Query)
- Convention for URI:
    - [DroidTerms Contract](http://udacity.github.io/DroidTermsExampleProvider-Documentation/)
    - [Calendar Contract](https://developer.android.com/reference/android/provider/CalendarContract.html)

## Resources

- [ConstraintLayout CodeLab](https://developer.android.com/codelabs/constraint-layout#0)





