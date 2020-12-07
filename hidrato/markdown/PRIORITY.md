# Priority

## Foreground Service

- Service user is actively aware
  - Android requires that service post a non-dismissible ongoing notifications
- Typically used to show the user real-time progress of long-running operation
- Android will prioritize foreground services
  - even if system memory is constrained
- Music Player that plays music long after you have navigated away from AudioPlayerActivity
- Getting directions via Google Maps after you have closed the App

## Consider four apps below:

- Which priority order would the system rank them in?
 
### Options
  
- A) GMail doing a background mail sync (3)
- B) Google Music playing a song in the background (2)
- C) The Camera app being used to take a photo (1)
- D) Google Maps in the background (4)
    - Maps is not visible or running any services
        - so it is most likely to be killed
        - if we were navigating, it would be completely different
    - GMail is running a service of some sort but not directly interacting with user
        - while Google Music and the Camera app are
    - Google Music is still a foreground service
        - so it has slightly lower priority
        - though neither will be killed unless memory pressure is exceptional
    - Camera app is pretty heavy-weight, it could be

## Resources

- [Notification Changes](https://www.youtube.com/watch?v=zGIw4MIJn5o&feature=youtu.be)
- [PendingIntent](https://developer.android.com/reference/kotlin/android/app/PendingIntent)
- [Create Notification](https://developer.android.com/training/notify-user/build-notification.html)
- [FirebaseJobDispathcer](https://github.com/googlearchive/firebase-jobdispatcher-android)
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)

