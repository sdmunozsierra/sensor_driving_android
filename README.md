# Driving Sensor Data Gathering

## Dependencies and Permissions

* Google Play Services
* Write External Storage (File Management)
* Access Fine Location (GPS Location)

##Location

Location is developed using __Google Play Services__. This is because the Android.Location services are not longer maintained and the documentatino recommend to switch to GP Services.

_If you're cloning from source:_ Download and set up [google play services](https://developers.google.com/android/guides/setup) in Android Studio. You must edit build.graddle to add the dependency and be sure you _update_ the version number each time [Google Play services](https://developers.google.com/android/guides/releases) is updated.
