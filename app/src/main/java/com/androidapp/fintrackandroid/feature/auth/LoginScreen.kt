package com.androidapp.fintrackandroid.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.androidapp.fintrackandroid.core.ui.component.FinTrackButton
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(FinTrackSpacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to FinTrack",
            style = MaterialTheme.typography.headlineMedium
        )

        FinTrackButton(
            text = "Continue",
            onClick = onLoginSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = FinTrackSpacing.large)
        )
    }
}