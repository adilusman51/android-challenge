package com.forloop.androidchallenge.domain.repository

import com.forloop.androidchallenge.domain.places.NearbyPlace

interface InMemoryStore {
    fun getNearbyPlaces(): List<NearbyPlace>
    fun setNearbyPlaces(places: List<NearbyPlace>)
}