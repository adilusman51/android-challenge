package com.forloop.androidchallenge.domain.state

import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.util.ErrorCode

data class PlacesState(
    val nearbyPlaces: List<NearbyPlace>? = null,
    val searchNearbyPlaces: List<NearbyPlace>? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val errorCode: ErrorCode? = null
)
