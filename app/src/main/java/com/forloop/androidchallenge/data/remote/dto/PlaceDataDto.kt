package com.forloop.androidchallenge.data.remote.dto

import com.forloop.androidchallenge.domain.places.PlaceData

data class PlaceDataDto(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String? = null,
    val partnerId: String? = null,
    val placeId: String? = null,
    val street: String? = null,
    val city: String? = null,
    val countryCode: String? = null,
    val zipCode: String? = null,
    val wifiCheckinEnabled: Boolean? = false
) {
    fun toPlaceData(): PlaceData {
        return PlaceData(
            id,
            lat,
            lon,
            name,
            street,
            city,
            countryCode,
            zipCode
        )
    }
}