package com.forloop.androidchallenge.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.forloop.androidchallenge.R
import com.forloop.androidchallenge.domain.places.PlaceData
import com.forloop.androidchallenge.presentation.component.ErrorMessage
import com.forloop.androidchallenge.presentation.component.Message
import com.forloop.androidchallenge.presentation.component.PlacesList
import com.forloop.androidchallenge.presentation.component.SearchBar
import com.forloop.androidchallenge.presentation.constants.DefaultListOfPlaces
import com.forloop.androidchallenge.presentation.theme.CodeChallengeTheme

@Composable
fun PlacesScreen(
    searchState: MutableState<TextFieldValue>,
    onChangeQuery: (TextFieldValue) -> Unit,
    places: List<PlaceData>?,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    error: String? = null,
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
            if (loading) {
                Message(message = stringResource(R.string.loading_desc))
            }
            error?.let {
                ErrorMessage(message = it)
            }
            PlacesList(
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
        PlacesScreen(
            searchState = searchState,
            places = DefaultListOfPlaces,
            onChangeQuery = {}
        )
    }
}