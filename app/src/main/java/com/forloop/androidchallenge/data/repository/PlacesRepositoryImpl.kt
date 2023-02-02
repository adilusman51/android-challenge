package com.forloop.androidchallenge.data.repository

import android.location.Location
import com.forloop.androidchallenge.data.remote.PlacesApi
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.repository.InMemoryStore
import com.forloop.androidchallenge.domain.repository.PlacesRepository
import com.forloop.androidchallenge.domain.util.ErrorCode
import com.forloop.androidchallenge.domain.util.Resource
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val api: PlacesApi,
    private val inMemoryStore: InMemoryStore
) : PlacesRepository {
    override suspend fun getNearbyPlacesData(location: Location?): Resource<List<NearbyPlace>> {
        return try {
            val results = api.getPlacesData()
            val data = results.toPlacesInfo()

            val nearbyPlaces: List<NearbyPlace> =
                data.places?.map { it.toNearbyPlace(location?.latitude, location?.longitude) }
                    ?.sortedWith(compareBy {
                        it.distance
                    }) ?: listOf()
            inMemoryStore.setNearbyPlaces(nearbyPlaces)
            Resource.Success(data = nearbyPlaces, message = "SUCCESS")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown message occurred.",
                errorCode = ErrorCode.ApiError
            )
        }
    }

    override suspend fun searchNearbyPlaces(query: String): List<NearbyPlace> {
        val nearbyPlaces = inMemoryStore.getNearbyPlaces()
        return if (query.isEmpty()) {
            nearbyPlaces
        } else {
            nearbyPlaces.filter { it.getSearch().contains(query, true) } ?: listOf()
        }
    }
}