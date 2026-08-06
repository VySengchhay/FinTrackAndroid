package com.androidapp.fintrackandroid.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackTheme

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(FinTrackSpacing.large),
        verticalArrangement = Arrangement.spacedBy(
            FinTrackSpacing.medium
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()

        message?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoadingContentPreview() {
    FinTrackTheme() {
        LoadingContent(
            message = "Loading"
        )
    }
}