package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.forloop.androidchallenge.R
import com.forloop.androidchallenge.domain.places.NearbyPlace
import com.forloop.androidchallenge.presentation.constants.DefaultNearbyPlace

@Composable
fun NearbyPlaceCard(
    nearbyPlace: NearbyPlace,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 8.dp,
) {

    val nearbyPlaceCardDesc = stringResource(id = R.string.nearby_place_card_desc)
    val placeNameDesc = stringResource(id = R.string.place_name_desc)
    val placeAddressDesc = stringResource(id = R.string.place_address_desc)
    val placeDistanceDesc = stringResource(id = R.string.place_distance_desc)
    Card(
        backgroundColor = backgroundColor,
        elevation = elevation,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = nearbyPlaceCardDesc }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${nearbyPlace.place.name}",
                modifier = Modifier.semantics { contentDescription = placeNameDesc })
            Text(
                text = nearbyPlace.place.getAddress(),
                modifier = Modifier.semantics { contentDescription = placeAddressDesc })
            Spacer(modifier = Modifier.height(4.dp))
            if (nearbyPlace.distance != null) {
                Text(
                    text = "${nearbyPlace.distance} meters",
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.semantics { contentDescription = placeDistanceDesc })
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    NearbyPlaceCard(
        nearbyPlace = DefaultNearbyPlace
    )
}