package com.forloop.androidchallenge.domain.places

import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.util.distanceBetween

data class PlaceData(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String?,
    val street: String?,
    val city: String?,
    val countryCode: String?,
    val zipCode: String?,
) {
    fun getSearch(): String {
        return "$name $street $city $countryCode $zipCode"
    }

    fun getAddress(): String {
        return "${street ?: ""} ${city ?: ""} ${countryCode ?: ""} ${zipCode ?: ""}"
    }

    fun toNearbyPlace(): NearbyPlace {
        return toNearbyPlace(null, null)
    }

    fun toNearbyPlace(fromLat: Double?, fromLon: Double?): NearbyPlace {
        return NearbyPlace(
            copy(),
            fromLat,
            fromLon,
            distanceBetween(lat, lon, fromLat, fromLon)
        )
    }

}
