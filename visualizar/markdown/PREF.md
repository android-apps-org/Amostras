# [Shared Preferences](https://developer.android.com/reference/android/content/SharedPreferences)

## [Preferences](https://developer.android.com/reference/androidx/preference/package-summary)

- Saves/changes shared preference file associated with key with new value
- Read from file to update setting for app
- Note: No type checking!

## SharedPreferenceChangeListener

- Triggered after any value is saved to SharedPreferences file
- SharedPreference object can register an object that implements
  - an onSharePreferenceChangeListener
  - this than gets called whenever a value in SharedPreference file changes
  - can trigger update in UI when preference changes
- Step 1: Determine Activity to call when preference changes
- Step 2: Activity needs to implement OnSharedPreferenceChangeListener
- Step 3: Link Listener to SharedPreference file (register)
- Step 4: Cleanup (unregister)

## PreferenceChangeListener

- Triggered before a value is saved to SharedPreferences file
  - this gives an opportunity for validation
- Flow:
  - User updates a preference
  - PreferenceChangeListener triggered for that preference
  - New value is saved to SharedPreference file
  - onSharedPreferenceChanged listeners are triggered

