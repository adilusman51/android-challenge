package com.forloop.androidchallenge.domain.use_case

import android.location.Location
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.repository.PlacesRepository
import com.forloop.androidchallenge.domain.util.Resource

class GetNearByPlaces(
    private val repository: PlacesRepository
) {

    suspend operator fun invoke(location: Location?): Resource<List<NearbyPlace>> {
        return repository.getNearbyPlacesData(location)
    }
}