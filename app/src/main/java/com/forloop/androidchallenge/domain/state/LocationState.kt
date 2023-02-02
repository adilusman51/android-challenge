package com.forloop.androidchallenge.domain.state

import android.location.Location
import com.forloop.androidchallenge.domain.util.ErrorCode

data class LocationState(
    val loading: Boolean = false,
    val currentLocation: Location? = null,
    val errorCode: ErrorCode? = null,
    val error: String? = null
)
