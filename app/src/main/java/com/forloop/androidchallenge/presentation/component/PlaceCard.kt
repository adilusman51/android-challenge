package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.forloop.androidchallenge.R
import com.forloop.androidchallenge.domain.places.PlaceData
import com.forloop.androidchallenge.presentation.constants.DefaultPlace

@Composable
fun PlaceCard(
    place: PlaceData,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 8.dp,
) {
    val placeCardDesc = stringResource(id = R.string.place_card_desc)
    val placeNameDesc = stringResource(id = R.string.place_name_desc)
    val placeAddressDesc = stringResource(id = R.string.place_address_desc)

    Card(
        backgroundColor = backgroundColor,
        elevation = elevation,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = placeCardDesc }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${place.name}",
                modifier = Modifier.semantics { contentDescription = placeNameDesc })
            Text(
                text = "${place.street ?: ""} ${place.city ?: ""} ${place.countryCode ?: ""} ${place.zipCode ?: ""}",
                modifier = Modifier.semantics { contentDescription = placeAddressDesc })
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    PlaceCard(
        place = DefaultPlace
    )
}