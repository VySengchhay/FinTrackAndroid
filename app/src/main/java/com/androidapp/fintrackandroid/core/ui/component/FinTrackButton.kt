package com.androidapp.fintrackandroid.core.ui.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackTheme

@Composable
fun FinTrackButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(
            min = FinTrackSpacing.componentHeight
        ),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
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