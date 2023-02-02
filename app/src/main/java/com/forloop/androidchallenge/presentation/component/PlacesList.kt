package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forloop.androidchallenge.R
import com.forloop.androidchallenge.domain.places.PlaceData
import com.forloop.androidchallenge.presentation.constants.DefaultListOfPlaces

@Composable
fun PlacesList(
    places: List<PlaceData>?,
    modifier: Modifier = Modifier
) {
    val placeListDesc = stringResource(R.string.places_list_desc)
    Column(modifier = modifier
        .fillMaxWidth()
        .semantics { contentDescription = placeListDesc }) {
        places?.let { data ->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                content = {
                    items(data) { place ->
                        PlaceCard(place = place)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    PlacesList(
        places = DefaultListOfPlaces
    )
}