package com.forloop.androidchallenge.domain.use_case

import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.repository.PlacesRepository

class SearchNearbyPlaces(
    private val repository: PlacesRepository
) {

    suspend operator fun invoke(
        query: String,
    ): List<NearbyPlace> {
        return repository.searchNearbyPlaces(query)
    }
}