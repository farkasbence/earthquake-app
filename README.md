# Earthquake app:

This is an app that fetches earthquake data from Geonames API, displays them in a list, and shows the earthquake location on a separate screen.

The application is an Android app written in Kotlin. It uses MVVM architecture, with the business logic lying in the viewmodels.
It uses Repository pattern to fetch the required data either from the service or from a local database.
Networking calls are made via Retrofit, and the JSON response is parsed with the Moshi library.

To access the Google Maps API, add your own GOOGLE_MAPS_API_KEY in the gradle.properties file
