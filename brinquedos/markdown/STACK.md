# Android Software Stack

## Linux Kernel (base)

- Handles low-level tasks
  - Hardware
  - Drivers
  - Power management

## On top of Linux Kernel

- C/C++ Libs
  - Lib C
  - SQLite
- Android Runtime
  - Core Android Libraries

## Application Framework

- App runs within its own instance of runtime
  - using classes/services provided in Application Framework

## Application Layer

- Includes all installed apps

## Run in Android Studio

- Code gets compiled into bytecode
  - that can be run in runtime on device
  - done via Gradle

