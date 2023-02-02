package com.forloop.androidchallenge.domain.repository

import android.location.Location
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.util.Resource

interface PlacesRepository {
    suspend fun getNearbyPlacesData(location: Location?): Resource<List<NearbyPlace>>

    suspend fun searchNearbyPlaces(query: String): List<NearbyPlace>
}