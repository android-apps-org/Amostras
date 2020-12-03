# [Permissions](https://developer.android.com/guide/topics/permissions/overview)

## Sandbox

- When each APK is installed, its given its won unique linux ID
- Each app runs in its own instance of Android runtime
- Each app is completely sand-boxed
  - its files, processes, and other resources are inaccessible to other apps
  - default: no app can access sensitive data
    - or perform actions that could adversely affect other apps, OS, or users
    - i.e. accessing internet, getting users location, reading/writing context database
- Declare permissions app requires in Manifest
- Since Android 6.0 (Marshmallow)
  - many permissions are granted without permissions dialog
  - sensitive permissions must be acknowledged by user when app is running
- Best Practice:
  - request minimum number of permissions
  - i.e. request Android to launch camera app and take picture on your behalf
    - instead of accessing camera directly

## Resources

- [App Permission Best Practices](https://developer.android.com/training/permissions/usage-notes)

