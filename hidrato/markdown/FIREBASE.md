# [Firebase Cloud Messaging](https://console.firebase.google.com/project/empurrador/overview)

## adb background commands

- adb shell am make-uid-idle <package>  (runs app in background)
- adb shell cmd appops set <package> RUN_IN_BACKGROUND deny  (force background limitations)
- adb shell cmd appops set <package> RUN_IN_BACKGROUND allow  (returns app to normal behavior based on target SDK)

## Resources

- [Notifications](https://developer.android.com/guide/topics/ui/notifiers/notifications.html)
- [Add Firebase](https://firebase.google.com/docs/android/setup#use_the_firebase_assistant)
- [Firebase Dispatcher](https://firebaseopensource.com/projects/firebase/firebase-jobdispatcher-android/)
- [Android Oreo](https://www.youtube.com/watch?v=7kD0ZYzJbYo)
- [Oreo Background Execution](https://www.youtube.com/watch?v=Pumf_4yjTMc)
- [Big View Style Notifications](https://developer.android.com/training/notify-user/expanded.html)
- [Big Picture Style Notifications](https://developer.android.com/reference/android/app/Notification.BigPictureStyle.html)
- [Prefer WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)

