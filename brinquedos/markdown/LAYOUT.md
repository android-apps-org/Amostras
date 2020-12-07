# Android Layout

## Android Apps

- Collections of connected components
  - that work with each other
  - and with the Android Framework
- AndroidManifest: where components are registered
- App Components
  - Activity
  - Service
  - Broadcast Receiver
  - Content Provider

## [Activity](https://developer.android.com/reference/android/app/Activity)
- responsible for most app user interaction
- single focused thing that a user can do
- responsible for creating the window that your application uses
  - to draw and receive events from the system
- creates views to show user information and to let user interact with activity
- from user perspective an app looks like a series of linked activities
- system maintains activities on a stack to help preserve context for users

## [Content Provider](https://developer.android.com/guide/topics/providers/content-providers)

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

