# Layouts

## Images

- svg files: preferred option for images in Android
  - can resize easily to work with any phone size and resolution
  - and does not affect quality of image
- res -> New -> Vector Asset -> Local file -> ` browse for file `
  - Size: check ` Override ` to use correct dimensions
  - check ` Enable auto mirroring for RTL layout `

## [Data Binding](https://developer.android.com/topic/libraries/data-binding)

- findViewById is expensive: requires traversing view hierarchy at runtime
- connects layout to activity/fragment at compile-time (layout inflation)
  - compiler generates helper class (binding class) when activity is created
  - can access view with generated binding class without any overhead

## [Accessibility](https://developer.android.com/guide/topics/ui/accessibility/apps#recommendations)

- Describe ImageViews, ImageButtons, Checkboxes with contentDescription attribute

## [Localization](https://developer.android.com/distribute/best-practices/launch/localization-checklist)

