package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forloop.androidchallenge.R

@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    onChangeText: (TextFieldValue) -> Unit,
    onClearText: () -> Unit,
) {
    val searchBarDesc = stringResource(id = R.string.searchbar_desc)
    val searchInputDesc = stringResource(id = R.string.search_input_desc)
    val clearButtonDesc = stringResource(id = R.string.clear_button_desc)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp)
            .semantics { contentDescription = searchBarDesc }
    ) {
        TextField(
            value = state.value,
            onValueChange = onChangeText,
            label = {
                Text(text = "Search")
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onClearText,
                    modifier = Modifier.semantics(mergeDescendants = true) {
                        contentDescription = clearButtonDesc
                    }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear_icon),
                        modifier = Modifier.size(24.dp),
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = searchInputDesc }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchBar(state = textState, onChangeText = {}, onClearText = {})
}