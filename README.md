# Pixel Art App Prototype

This repository contains a minimal Android prototype written in Kotlin. It provides a simple pixel art drawing surface with preliminary implementations of:

* Layer support
* Masking of drawing areas
* A basic color circle for selecting colors

The project uses the standard Android Gradle build system. The Gradle wrapper is
included without the `gradle-wrapper.jar` binary so running `./gradlew` will
download it on first use. To build or test the app run:

```bash
./gradlew assembleDebug
```

Note: network access may be required to download Android build tools and dependencies.
