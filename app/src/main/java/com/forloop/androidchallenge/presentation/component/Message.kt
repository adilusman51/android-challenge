package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forloop.androidchallenge.R
import com.forloop.androidchallenge.presentation.theme.CodeChallengeTheme

@Composable
fun Message(message: String, modifier: Modifier = Modifier) {
    val messageDesc = stringResource(id = R.string.message_desc)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(16.dp, 8.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = messageDesc
            }
    ) {
        Text(
            text = message,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CodeChallengeTheme {
        Message("When I will be older, I will be a longer message.")
    }
}