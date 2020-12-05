# Android Lifecycle

## Activity

- Possible to create different layouts and resources for different device configurations
    - from screen size to pixel density to device orientation
    - device orientation and screen width can change at runtime
    - so default behavior is to destroy and re-create android activities
        - when device configuration changes
- Rotate Device:
    - onCreate, onStart, onResume
    - onPause: reason onPause was not called, content of text views that are 
        - populated dynamically, like log view, are not automatically preserved
        - when activity is destroyed and re-created

## GitHub Query

- Rotate Device
- Search Term from EditText is preserved
- But lose URL and Query results
- EditText: can be edited by users
    - so preserved automatically by Android framework during configuration change (i.e. rotation)
- Regular TextViews are not preserved
    - use saveInstanceState

