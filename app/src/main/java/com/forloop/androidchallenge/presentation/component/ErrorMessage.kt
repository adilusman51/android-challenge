package com.forloop.androidchallenge.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
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
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    val errorDesc = stringResource(id = R.string.error_desc)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.error)
            .padding(16.dp, 8.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = errorDesc
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.Default.Warning,
                contentDescription = stringResource(R.string.error_icon),
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = message,
                color = MaterialTheme.colors.onPrimary
            )
        }
        if (onRetry != null) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedButton(onClick = onRetry) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CodeChallengeTheme {
        ErrorMessage("An exception occurred")
    }
}