package com.androidapp.fintrackandroid.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay

@Composable
fun FinTrackBottomBar(
    currentRoute: NavKey?,
    onDestinationSelected:(BottomDestination) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        bottomDestination.forEach { destination ->
            NavigationBarItem(
                modifier = Modifier,
                selected = currentRoute == destination.route,
                onClick = { onDestinationSelected(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = {
                    Text(
                        text = destination.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }
    }
}