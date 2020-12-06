# Android Architecture Components

## [Room](https://developer.android.com/topic/libraries/architecture/room)

- Object Relational Mapping (ORM) Library
- SQL validation at compilation
- Built to work with LiveData and RxJava for data observation
- Annotations:
    - @Entity: database tables
    - @DAO: API
    - @Database: 

## [LiveData (Observer Pattern)](https://developer.android.com/topic/libraries/architecture/livedata)

- Observable data holder
- Sits between database and UI
- Monitors changes in database and notifies observers when data changes
- Subject (LiveData) keeps list of Observers subscribed to it
    - notifies them when there is any relevant changes
    - when data changes, set value method on LiveData object will be called
    - which will trigger call to method in each of the Observers

## [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

- Allows data to survive configuration changes such as rotation
- Lifecycle starts once Activity is created and lasts until it is finished
- So we can cache complex data in ViewModel
- When Activity is re-created after rotation, it will use same ViewModel object in cache data
- Can make asynchronous calls without overhead of preventing memory leaks (concern when no VM)
    - i.e. Activity is destroyed before call finishes

## [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle)

- Consider if not in Activity that receives update
- LiveData is a Lifecycle aware component
    - knows state of associated component
    - activity is started/resumed (considered active)
        - Observers will be notified
    - if in act as activity when database is updated
        - main activity is not active and observers will not be notified in main activity
    - when back button is pressed
        - main activity becomes active again
        - Observers are notfied and UI is updated
- LiveData will also know when the state of activity is destroyed
    - automatically unsubscribes Observers to avoid memory leaks
- Lifecycle Owner: 
    - have lifecycle like Activties and Fragments
- Lifecycle Observer:
    - observe lifecycle owners and get notified on lifecycle changes
- LiveData is lifecycle aware because it is a Lifecycle Observer

## Resources

- [POJO](https://en.wikipedia.org/wiki/Plain_old_Java_object)
- [@Ignore Annotation](https://developer.android.com/reference/android/arch/persistence/room/Ignore.html)
- [SQL cheat sheet](https://d17h27t6h515a5.cloudfront.net/topher/2016/September/57ed880e_sql-sqlite-commands-cheat-sheet/sql-sqlite-commands-cheat-sheet.pdf)
- [Race Condition](https://en.wikipedia.org/wiki/Race_condition)
- [Executor](https://developer.android.com/reference/java/util/concurrent/Executor.html)
- [MainThreadExecutor example](https://github.com/android/architecture-components-samples/blob/b1a194c1ae267258cd002e2e1c102df7180be473/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.java)
- [How to add Dependencies](https://developer.android.com/jetpack/androidx/releases/lifecycle)

