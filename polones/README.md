# [Android Material Design](https://material.io/develop/android)

## [Color Palettes](https://material.io/design/color/the-color-system.html#color-usage-and-palettes)

- Primary: main color base for app
  - View and Components like MenuBar
- Accent: typically brighter
  - draws attention to key elements like buttons
- [Material Design Palette](https://www.materialpalette.com/)

## [Fonts](https://material.io/design/typography/the-type-system.html#type-scale)

- Android default text: Roboto
- Android font-family default: sans-serif
- Typically should stick with one font-family throughout an app
  - change color, weight, size to make text stand out
- Only have a few consistent styles for different text components
  - i.e. headline article text should be bigger or bolder than subtitle or body
- Text is in units of sp (scale-independent pixels)
  - stays same physical size across different resolution screens
  - sp is also used for accessibility purpose (unlike dp)
    - if text size settings are changed to be larger for visibility
    - Android will enlarge any view whose size is in sp accordingly

## Screen Density

- Size
- Density: number of pixels in physical area of the screen (dpi)
  - buckets: mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi
  - use dp (density-independent pixels) for same physical size across all screen resolutions
  - higher-density screens will be made of more pixels
  - design for size not pixel by pixel

## Resource (res)

- many of the resource values can change at runtime
  - most common being orientation
- layout and all resources in it could be completely different
  - layout for German words to account for really long German words
  - more typically for combination of screen size and device configuration
- for this reason android activities are destroyed and re-created when device configuration changes
- [UI Widgets](https://developer.android.com/guide/topics/ui)

