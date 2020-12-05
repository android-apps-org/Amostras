# Data Persistence

## Options

- Bundle (savedInstanceState):
    - key/value pairs
    - i.e. used in saving state during app rotation
    - or system destroys activity because of memory constraints
    - should be used as a temporary store
    - and only use it when user is actively using app
    - if user uses back button to quit app or restarts phone (data is gone)
- SharedPreferences:
    - save simple key/value pair(s) to file
    - as long as phone works and app has not been uninstalled, data will be there
    - save user data (name, last web page to re-open when app launches)
- SQLite Database
- Internal/External Storage
- Cloud (Firebase)

## Preferences

- SharedPreferenceChangeListener:
    - triggered after any value is saved to SharedPreferences file
- PreferenceChangeListener:
    - triggered before a value is saved to SharedPreferences file
    - chance to prevent invalid update to a preference 
    - attached to single preference

