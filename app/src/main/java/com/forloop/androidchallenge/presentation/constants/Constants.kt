package com.forloop.androidchallenge.presentation.constants

import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.places.PlaceData
import com.forloop.androidchallenge.domain.places.PlacesInfo

val DefaultPlace: PlaceData = PlaceData(
    id = 123,
    lat = 48.1351,
    lon = 11.5820,
    name = "McDonalds",
    street = "street",
    city = "Munich",
    countryCode = "DE",
    zipCode = "80331",
)

val DefaultNearbyPlace: NearbyPlace = NearbyPlace(
    place = PlaceData(
        id = 123,
        lat = 48.1351,
        lon = 11.5820,
        name = "McDonalds",
        street = "street",
        city = "Munich",
        countryCode = "DE",
        zipCode = "80331"
    ),
    fromLat = 48.1351,
    fromLon = 11.5820,
    distance = 0.0f

)

val DefaultListOfPlaces: List<PlaceData> = listOf(
    DefaultPlace
)

val DefaultListOfNearbyPlaces: List<NearbyPlace> = listOf(
    DefaultNearbyPlace
)

val DefaultPlaceInfo: PlacesInfo = PlacesInfo(DefaultListOfPlaces)