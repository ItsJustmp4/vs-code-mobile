# VS Code Mobile

A lightweight Android mockup inspired by the VS Code interface. It includes:

- Dark editor layout with a VS Code-like sidebar
- Multi-tab code editing surface
- Plugin marketplace screen with installable extension cards
- Demo plugin data and mobile-friendly theming
- GitHub Actions workflow for building an APK

## Features

- Mobile editor style inspired by Visual Studio Code
- Explorer panel and file tabs
- Simulated plugin marketplace
- Sample plugin packages stored in `app/src/main/assets/plugins.json`
- CI build pipeline using GitHub Actions

## Run locally

1. Install Android Studio or Android SDK 34+
2. Open the project in Android Studio
3. Sync the Gradle files
4. Run the `app` configuration on an emulator or connected Android device

If you want to generate Gradle wrapper files locally:

```bash
gradle wrapper --no-daemon
./gradlew assembleDebug
```

## Build in GitHub Actions

The workflow is located at `.github/workflows/build.yml` and will create a debug APK automatically.

## Notes

This project is a mobile-inspired editor concept rather than a full VS Code codebase clone. It focuses on the visual language and plugin marketplace experience while staying lightweight enough for a mobile app demo.