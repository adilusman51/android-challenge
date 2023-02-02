package com.forloop.androidchallenge.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.presentation.component.ErrorMessage
import com.forloop.androidchallenge.presentation.component.NearbyPlacesList
import com.forloop.androidchallenge.presentation.component.SearchBar
import com.forloop.androidchallenge.presentation.constants.DefaultListOfNearbyPlaces
import com.forloop.androidchallenge.presentation.theme.CodeChallengeTheme

@Composable
fun NearbyPlacesScreen(
    searchState: MutableState<TextFieldValue>,
    onChangeQuery: (TextFieldValue) -> Unit,
    places: List<NearbyPlace>?,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    error: String? = null,
    loadingLocation: Boolean = false,
    errorLocation: String? = null,
    onRetryLocation: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                state = searchState,
                onChangeText = onChangeQuery,
                onClearText = { onChangeQuery(TextFieldValue("")) })

            errorLocation?.let {
                ErrorMessage(
                    message = it,
                    onRetry = onRetryLocation
                )
            }
            if (loading || loadingLocation) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            error?.let {
                ErrorMessage(message = it)
            }
            NearbyPlacesList(
                places
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val searchState = remember { mutableStateOf(TextFieldValue("")) }
    CodeChallengeTheme {
        NearbyPlacesScreen(
            searchState = searchState,
            places = DefaultListOfNearbyPlaces,
            onChangeQuery = {}
        )
    }
}