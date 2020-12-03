# [Gradle](https://docs.gradle.org/current/samples/sample_building_android_apps.html)

## Overview

- Build toolkit that manages dependencies
- Allows you to find custom build logic
- Build system of choice for Android
- Run -> if project has changed build scripts will run
- Make Project (green hammer icon)
  - explicitly run build scripts

## Task

- Represents single, atomic piece of work for a build
- List of tasks: Gradle -> Project Name -> Tasks
- Click on any task to run task
  - i.e. android -> signingReport
  - generates SHA-1 Fingerprint for app

## Commandline

- ./gradlew tasks
  - lists all tasks
- run chmod +x on gradlew for permission

