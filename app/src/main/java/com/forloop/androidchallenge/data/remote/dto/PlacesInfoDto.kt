package com.forloop.androidchallenge.data.remote.dto

import com.forloop.androidchallenge.domain.places.PlaceData
import com.forloop.androidchallenge.domain.places.PlacesInfo

data class PlacesInfoDto(
    val places: List<PlaceDataDto>? = null
) {
    fun toPlacesInfo(): PlacesInfo {
        val placeDataList: List<PlaceData>? = places?.map { place -> place.toPlaceData() }
        return PlacesInfo(placeDataList)
    }
}
