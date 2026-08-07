package com.androidapp.fintrackandroid.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackTheme

@Composable
fun FinTrackButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingText: String = "Loading"
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(
            min = FinTrackSpacing.componentHeight
        ),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium
    ) {
        if (isLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(
                        FinTrackSpacing.large
                    ),
                    color = LocalContentColor.current,
                    strokeWidth = FinTrackSpacing.extraSmall
                )

                Spacer(
                    modifier = Modifier
                        .width(FinTrackSpacing.small)
                )

                Text(
                    text = loadingText,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun FinTrackButtonPreview() {
    FinTrackTheme() {
        FinTrackButton(
            text = "Button",
            onClick = {}
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun FinTrackButtonLoadingPreview() {
    FinTrackTheme() {
        FinTrackButton(
            text = "Button",
            onClick = {},
            isLoading = true,
            loadingText = "Signing in"
        )
    }
}