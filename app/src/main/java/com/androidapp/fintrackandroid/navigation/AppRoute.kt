package com.androidapp.fintrackandroid.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface FinTrackRoute : NavKey
sealed interface BottomRoute: FinTrackRoute

@Serializable
data object SplashRoute : FinTrackRoute

@Serializable
data object LoginRoute : FinTrackRoute

@Serializable
data object DashboardRoute : BottomRoute

@Serializable
data object TransactionsRoute: BottomRoute

@Serializable
data object BudgetsRoute: BottomRoute

@Serializable
data object RecurringBillsRoute: BottomRoute
