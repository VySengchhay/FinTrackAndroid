package com.androidapp.fintrackandroid.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.androidapp.fintrackandroid.feature.auth.LoginScreen
import com.androidapp.fintrackandroid.feature.auth.SplashScreen
import com.androidapp.fintrackandroid.feature.budgets.BudgetsScreen
import com.androidapp.fintrackandroid.feature.dashboard.DashboardScreen
import com.androidapp.fintrackandroid.feature.recurringbill.RecurringBillsScreen
import com.androidapp.fintrackandroid.feature.transactions.TransactionsScreen

@Composable
fun FinTrackApp() {
    val activity = LocalActivity.current
    val backStack = rememberNavBackStack(SplashRoute)
    val currentRoute = backStack.lastOrNull()

    fun replaceBackStack(route: FinTrackRoute) {
        backStack.clear()
        backStack.add(route)
    }

    fun selectBottomDestination(
        destination: BottomDestination
    ) {
        val selectedRoute = destination.route

        if (backStack.lastOrNull() == selectedRoute) {
            return
        }

        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }

        if (backStack.firstOrNull() != DashboardRoute) {
            backStack.clear()
            backStack.add(DashboardRoute)
        }

        if (selectedRoute != DashboardRoute) {
            backStack.add(selectedRoute)
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute is BottomRoute) {
                FinTrackBottomBar(
                    currentRoute = currentRoute,
                    onDestinationSelected = ::selectBottomDestination
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = {
                if (backStack.size > 1) {
                    backStack.removeLastOrNull()
                } else {
                    activity?.finish()
                }
            },
            entryProvider = entryProvider {
                entry<SplashRoute> {
                    SplashScreen(
                        onFinished = {
                            replaceBackStack(LoginRoute)
                        }
                    )
                }

                entry<LoginRoute> {
                    LoginScreen(
                        onLoginSuccess = {
                            replaceBackStack(DashboardRoute)
                        }
                    )
                }

                entry<DashboardRoute> {
                    DashboardScreen()
                }

                entry<TransactionsRoute> {
                    TransactionsScreen()
                }

                entry<BudgetsRoute> {
                    BudgetsScreen()
                }

                entry<RecurringBillsRoute> {
                    RecurringBillsScreen()
                }
            }
        )
    }
}
