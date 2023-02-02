package com.forloop.androidchallenge.data.local

import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.repository.InMemoryStore

class InMemoryStoreImpl(
    private var nearbyPlaces: List<NearbyPlace>
) : InMemoryStore {
    override fun getNearbyPlaces(): List<NearbyPlace> {
        return nearbyPlaces
    }

    override fun setNearbyPlaces(places: List<NearbyPlace>) {
        nearbyPlaces = places
    }
}