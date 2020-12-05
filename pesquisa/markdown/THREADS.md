# AsyncTask

## Threads

- Android throws exception (NetworkOnMainThreadException) when trying to access network on main thread
- Each Android app can be divided into multiple threads of execution
  - that can all run concurrently
  - these threads of execution can be scheduled by OS
  - to run on different CPU cores
  - but system might choose to time-slice them on single CPU
- Android apps have single UI thread
  - responsible for getting events from various sensors and setting up next frame to draw
  - to run at an ideal 60 frames/second
    - need to make sure all computations between draws takes less than 17ms
    - 60 FPS < 17ms (want to do as little as possible)
    - networking would freeze app while it was loading (if done on main thread)
    - after 5 seconds of ignoring user input
      - Android would prompt user to close app
- [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask)
  - deprecated in API level 30
  - Allows running task on background thread while publishing results to UI thread

## [UI Thread](https://medium.com/@devDeeJay/behind-android-ui-thread-d9701caa3319)

- Message Queue/Handler
  - Allows sending, processing runnable objects and messages, often from other threads
- AsyncTask wraps this behavior with reasonably straight-forward interface
- Generic Class takes parameterized types in constructor:
  - Params: type sent to task upon execution
  - Progress: type published to update progress during background computation
  - Result: type of result of background computation
- Overridable methods (correspond to parameters):
  - doInBackground (2): called on another thread where long running task will run
  - onProgressUpdate (3): publishProgress (optional)
  - onPostExecute (4): return result when background task is complete (back on UI thread)
  - onPreExecute (1): called on UI thread (initializations on UI thread before background task starts)

## Resources

- [Processes and Threads](https://developer.android.com/guide/components/processes-and-threads)

