package com.androidapp.fintrackandroid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomDestination(
    val label: String,
    val icon: ImageVector,
    val route: BottomRoute,
) {
    Home(
        label = "Home",
        icon = Icons.Filled.Home,
        route = DashboardRoute
    ),
    Transactions(
        label = "Transactions",
        icon = Icons.AutoMirrored.Filled.List,
        route = TransactionsRoute
    ),
    Budget(
        label = "Budget",
        icon = Icons.Filled.Money,
        route = BudgetsRoute
    ),
    RecurringBill(
        label = "Recurring Bill",
        icon = Icons.Filled.CreditCard,
        route = RecurringBillsRoute
    )
}

val bottomDestination = BottomDestination.entries
