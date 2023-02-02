package com.forloop.androidchallenge.domain.use_case

import android.location.Location
import com.forloop.androidchallenge.domain.location.LocationTracker

class GetCurrentLocation(
    private val locationTracker: LocationTracker
) {
    suspend operator fun invoke(): Location? {
        return locationTracker.getCurrentLocation()
    }
}