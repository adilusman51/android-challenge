package com.forloop.androidchallenge.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.forloop.androidchallenge.presentation.constants.DefaultListOfNearbyPlaces
import com.forloop.androidchallenge.presentation.screen.NearbyPlacesScreen
import com.forloop.androidchallenge.presentation.theme.CodeChallengeTheme
import com.forloop.androidchallenge.presentation.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PlacesViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            initWithLocation()
        }

        showLocationPermissions()

        setContent {
            CodeChallengeTheme {
                NearbyPlacesScreen(
                    places = viewModel.getNearbyPlaces(),
                    searchState = viewModel.nearbySearchQuery,
                    onChangeQuery = viewModel::setNearbySearchQuery,
                    loading = viewModel.placesState.loading,
                    error = viewModel.placesState.error,
                    loadingLocation = viewModel.locationState.loading,
                    errorLocation = viewModel.locationState.error,
                    onRetryLocation = {
                        showLocationPermissions()
                    }
                )
            }
        }
    }

    private fun showLocationPermissions() {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    private fun initWithLocation() {
        viewModel.getPlacesInfoWithLocation()
    }

    private fun initWithoutLocation() {
        viewModel.getPlacesInfo()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val searchState = remember { mutableStateOf(TextFieldValue("")) }
    CodeChallengeTheme {
        NearbyPlacesScreen(
            searchState = searchState,
            places = DefaultListOfNearbyPlaces,
            onChangeQuery = {}
        )
    }
}