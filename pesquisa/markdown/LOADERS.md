# [Loaders](https://developer.android.com/guide/components/loaders)

## Why

- device is rotated during network transaction
- when activity is destroyed and recreated, app continues to run
    - all running threads continue to process
    - in github query, it delivers results to zombie activity (has gone away)
    - activity is left empty
- i.e. sunshine app:
    - create async task in onCreate, 
        - starts thread which starts a background task
        - if device is rotated or anything that causes activity to be re-started
        - new activity will create another async task to do background operation
        - will create extra network usage as both threads run in parallel
        - background threads will deliver result to callback that's part of activity,
        - async tasks will keep all old zombie activities around as long as threads are running
        - causing extra memory pressure

## Loaders (deprecated from API 28)

- Provide framework to perform asynchronous loading of data
- Registered by Id (Loader ID) with component, LoaderManager
- Which allows them to live beyond lifecycle of activity they are associated with
- Prevents duplicate loads from happening in parallel

