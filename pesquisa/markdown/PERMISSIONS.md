# [Permissions](https://developer.android.com/guide/topics/permissions/overview)

## Sandbox

- When each APK is installed, its given its won unique linux user ID
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

## Install-time Permissions

- Give your app limited access to restricted data
  - they allow your app to perform restricted actions that minimally affect system or other apps
- When you declare install-time permissions in your app
  - system automatically grants your app permissions when user installs your app

## Normal Permissions (Install-time sub-type)

- allow access to data and actions that extend beyond your app sandbox
- but data and actions present very little risk to user privacy and operation of other apps

## Signature Permissions (Install-time sub-type)

- if app declares a signature permission that another app has defined,
  - and if two apps are signed by same certificate,
  - then system grants permission to the first app at install time
  - otherwise, that first app cannot be granted the permission

## Runtime Permissions

- Dangerous permissions
- Gives your app additional access to restricted data
  - and they allow your app to perform restricted actions that more substantially affect system and other apps
- Therefore, you need to request runtime permissions in your app
  - before you can access restricted data or perform restricted actions

## Resources

- [App Permission Best Practices](https://developer.android.com/training/permissions/usage-notes)
- [Manifest Permissions](https://developer.android.com/reference/android/Manifest.permission)

