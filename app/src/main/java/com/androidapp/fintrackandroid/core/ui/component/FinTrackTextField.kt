package com.androidapp.fintrackandroid.core.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackTheme

@Composable
fun FinTrackTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    visualTransformation: VisualTransformation =
        VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        isError = isError,
        supportingText = supportingText?.let { text ->
            {
                Text(text = text)
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = MaterialTheme.shapes.medium
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun FinTrackTextFieldPreview() {
    FinTrackTheme() {
        FinTrackTextField(
            value = "",
            onValueChange = {},
            label = "Username:",
            modifier = Modifier.padding(FinTrackSpacing.large)
        )
    }
}