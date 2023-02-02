package com.forloop.androidchallenge.domain.util

import android.location.Location

fun distanceBetween(
    firstLat: Double? = null,
    firstLon: Double? = null,
    secondLat: Double? = null,
    secondLon: Double? = null
): Float? {
    if (firstLat == null || firstLon == null || secondLat == null || secondLon == null) return null
    val results: FloatArray = floatArrayOf(0.0f)
    Location.distanceBetween(firstLat, firstLon, secondLat, secondLon, results)
    return results[0]
}
