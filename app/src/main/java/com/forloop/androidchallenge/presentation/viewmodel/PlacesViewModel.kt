package com.forloop.androidchallenge.presentation.viewmodel

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.domain.state.LocationState
import com.forloop.androidchallenge.domain.state.PlacesState
import com.forloop.androidchallenge.domain.use_case.GetCurrentLocation
import com.forloop.androidchallenge.domain.use_case.GetNearByPlaces
import com.forloop.androidchallenge.domain.use_case.SearchNearbyPlaces
import com.forloop.androidchallenge.domain.util.ErrorCode
import com.forloop.androidchallenge.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val getNearByPlaces: GetNearByPlaces,
    private val getCurrentLocation: GetCurrentLocation,
    private val searchNearbyPlaces: SearchNearbyPlaces,
) : ViewModel() {

    val nearbySearchQuery = mutableStateOf(TextFieldValue(""))

    var placesState by mutableStateOf(PlacesState())
        private set

    var locationState by mutableStateOf(LocationState())
        private set

    private var searchNearbyJob: Job? = null


    fun setNearbySearchQuery(query: TextFieldValue) {
        nearbySearchQuery.value = query
        searchNearbyJob?.cancel()
        searchNearbyJob = viewModelScope.launch {
            val nearbyPlaces = searchNearbyPlaces(query.text)
            placesState =
                placesState.copy(
                    searchNearbyPlaces = nearbyPlaces
                )
        }
    }

    fun getNearbyPlaces(): List<NearbyPlace>? {
        return if (nearbySearchQuery.value.text.isEmpty()) {
            placesState.nearbyPlaces
        } else {
            placesState.searchNearbyPlaces
        }
    }

    fun getPlacesInfo(location: Location? = null) {
        viewModelScope.launch {
            placesState = placesState.copy(
                nearbyPlaces = null,
                loading = true,
                error = null,
                errorCode = null
            )
            when (val results = getNearByPlaces(location)) {
                is Resource.Success -> {
                    placesState = placesState.copy(
                        loading = false,
                        nearbyPlaces = results.data,
                        error = null,
                        errorCode = results.errorCode,
                    )
                }
                is Resource.Error -> {
                    placesState = placesState.copy(
                        loading = false,
                        nearbyPlaces = null,
                        error = results.message,
                        errorCode = results.errorCode,
                    )
                }
            }
        }
    }

    fun getPlacesInfoWithLocation() {
        viewModelScope.launch {
            locationState = locationState.copy(
                loading = true,
            )

            val currentLocation = getCurrentLocation()

            locationState = locationState.copy(
                loading = false,
                currentLocation = currentLocation,
                errorCode = if (currentLocation == null) {
                    ErrorCode.LocationPermissionError
                } else null,
                error = if (currentLocation == null) {
                    "Could not retrieve location. Kindly, make sure that GPS is enabled and location permissions are granted to this app."
                } else null
            )

            getPlacesInfo(currentLocation)
        }
    }


}