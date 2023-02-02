package com.forloop.androidchallenge.domain.places

data class NearbyPlace(
    val place: PlaceData,
    val fromLat: Double?,
    val fromLon: Double?,
    val distance: Float? = null,
) {
    fun getSearch(): String {
        return "${place.name} ${place.street} ${place.city} ${place.countryCode} ${place.zipCode}"
    }
}
